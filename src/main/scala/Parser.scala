/**
 * Created by Dakota on 6/1/2015.
 */

import fastparse.core.Result
import fastparse.core.Result.{Failure, Success}
import scala.io.Source
import fastparse._
import scala.util.Try

object Transcription {

  // TODO
  abstract class Chord

  case class Phrase()

  case class Title(title: String)

  case class Artist(artist: String)

  case class Meter(meter: String)

  case class Key(key: String)

  case class Properties(title: Title, artist: Artist, meter: Meter, key: Key)

}

class BillboardParser {
  /*
   Based on "Parsing the Billboard Chord Transcriptions"
   by W. Bas de Haas and John Ashley Burgoyne
   http://www.cs.uu.nl/research/techreps/repo/CS-2012/2012-018.pdf
   See also:
   http://ismir2011.ismir.net/papers/OS8-1.pdf
   http://ismir2011.ismir.net/papers/PS4-14.pdf
   */

  // Useful primitives
  val whitespace = P(CharsWhile(" \n\t".contains(_)).?)
  val string = P(CharsWhile(!" \n|,<>-".contains(_)).!)
  val chordinternals = P(CharsWhile(!" \n|<>-".contains(_)).!)
  val line = P(CharsWhile(!"\n".contains(_)).! ~ "\n")
  val digits = P(CharsWhile('0' to '9' contains _)).!
  val exponent = P(CharIn("eE") ~ CharIn("-+").? ~ digits.!)
  val fractional = P(digits.! ~ "." ~ digits.! ~ exponent.?)
  val word = P(CharsWhile(('A' to 'z').contains(_)))
  val wordstring = P(word.rep(sep = " "))
  val upperchar = P(CharIn('A' to 'Z'))
  val lowerchar = P(CharIn('a' to 'z'))

  def timestamp(s: (String, String, Option[String])) = {
    // TODO
    // if exp clamp to 0 since exp SHOULD only be used for hundredths or smaller
    s match {
      case (num, dec, exp) => exp match {
        case None => (num + dec) toFloat
        case Some(x) => 0f
      }
    }
  }

  // Musical labels
  val basic = P("pre-intro" | "intro" | "verse" | "chorus" | "bridge" | "refrain" | "theme")
  val instrumental = P("instrumental" | "solo" | "fade" ~ ("in" | "-in" | " in"))
  val transition = P("transition" | "pre" ~ CharIn("- ").? ~ StringIn("chorus", "verse")
    | "interlude" | "trans" | "modulation" | "key change")
  val genrespecific = P("head" | "main theme" | "secondary theme" | "theme")
  val formspecific = P("exposition" | "development" | "recapitulation")
  val ending = P("outro" | "coda" | "ending" | "fade" ~ ("out" | "-out" | " out"))
  val noise = P("applause" | "noise" | "talking")
  val songend = P("end")
  val silence = P("silence")
  val sectionlabel = P(upperchar ~ "'".rep)
  val pause = P("&pause")

  val functionword = P((sectionlabel | basic | instrumental |
    transition | genrespecific | formspecific |
    ending | songend | noise | silence).! ~ ((" " ~ word) | ("-" ~ lowerchar)).?)

  // Instrument annotations
  val istart = P("(" ~ wordstring)
  val iend = P(wordstring ~ ")")
  val inst = P("(" ~ wordstring ~ ")")
  val instrument = P(inst | istart | iend)

  // Metadata
  val title = P("# title: " ~ line)
  val artist = P("# artist: " ~ line)
  val meter = P("# metre: " ~ line)
  val key = P("# tonic: " ~ line)
  val comment = P(meter | key)
  val properties = P(title ~! artist ~! ((meter ~ key) | (key ~ meter)))

  // Chords and phrases
  val repeat = "x" ~ digits
  val elision = "->"
  val phraseannotation = P(repeat | elision)

  val chordstring = P((chordinternals | pause).rep(sep = " "))
  val chordlist = P("| " ~! chordstring.!.rep(sep = " | " ~ !phraseannotation) ~ " |" ~ " ".?)
  val chordlists = P(chordlist.rep(1))

  val event = P(functionword | chordlist.! ~ phraseannotation.!.? | instrument)
  val events = P(event.rep(sep = ", "))

  val phrase = P(fractional ~ whitespace ~ events.! ~ whitespace)

  // Top-level parser
  val transcription = properties.! ~! whitespace ~! (phrase | comment).rep(1).!
}

object Parser extends BillboardParser {
  val basedir = "C:/chords/McGill-Billboard/"

  def getPath(songId: String): String = {
    basedir + songId + "/salami_chords.txt"
  }

  def parseTranscription(songId: String): Try[Result[_]] =
    Try(transcription.parse(Source.fromFile(getPath(songId)).mkString))

  def printTranscription(trans: Result[_], songId: String) = {
    trans match {
      case s: Success[_] => println(s)
      case f: Failure => println(s"Parse error on $songId: $f")
    }
  }

  def main(args: Array[String]) {
    for (songId <- 1 to 3 map { id => f"$id%04d" }) {
      parseTranscription(songId) match {
        case scala.util.Success(t) => printTranscription(t, songId)
        case scala.util.Failure(e) => e match {
          case e: java.io.FileNotFoundException => ()
          case e: Exception => println(s"Error processing $songId: ${e.getMessage}")
        }
      }
    }
  }
}



