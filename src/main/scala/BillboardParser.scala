/**
 * Created by Dakota on 6/24/2015.
 */
package billboardparser

import fastparse._

class BillboardParser {
  /*
   Based on "Parsing the Billboard Chord Transcriptions"
   by W. Bas de Haas and John Ashley Burgoyne:
   http://www.cs.uu.nl/research/techreps/repo/CS-2012/2012-018.pdf
   See also:
   http://ismir2011.ismir.net/papers/OS8-1.pdf
   http://ismir2011.ismir.net/papers/PS4-14.pdf
   */

  // Useful primitives
  val whitespace = P(CharsWhile(" \n\t".contains(_)).?)
  val string = P(CharsWhile(!" \n|,<>-".contains(_)).!)
  val chordinternals = P(CharsWhile(!" \n|<>-".contains(_)).!)
  // map Transcription.Chord.apply
  val line = P(CharsWhile(!"\n".contains(_)).! ~ "\n")
  val digits = P(CharsWhile('0' to '9' contains _)).!
  val exponent = P(CharIn("eE") ~ CharIn("-+").? ~ digits.!)
  val fractional = P(digits.! ~ "." ~ digits.! ~ exponent.?)
  val word = P(CharsWhile(('A' to 'z').contains(_)))
  val wordstring = P(word.rep(sep = " ")).!
  val upperchar = P(CharIn('A' to 'Z'))
  val lowerchar = P(CharIn('a' to 'z'))

  def timestamp(s: (String, String, Option[String])) = {
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
  val pause = P("&pause") map (_ => "pause") //Transcription.Chord("pause"))

  val functionword = P((sectionlabel | basic | instrumental |
    transition | genrespecific | formspecific |
    ending | songend | noise | silence).! ~ ((" " ~ word) | ("-" ~ lowerchar)).?)

  // Instrument annotations
  val istart = P("(" ~ wordstring)
  val iend = P(wordstring ~ ")")
  val inst = P("(" ~ wordstring ~ ")")
  val instrument = P(inst | istart | iend)

  // Metadata
  val title = P("# title: " ~ line).!.map(Transcription.Title)
  val artist = P("# artist: " ~ line).!.map(Transcription.Artist)
  val meter = P("# metre: " ~ line).!.map(Transcription.Meter)
  val key = P("# tonic: " ~ line).!.map(Transcription.Key)

  // because apparently music grad students can't be consistent when annotating

  val comment = P(meter | key)

  val properties = P(title
    ~! artist
    ~! (meter ~ key | key ~ meter)) //.map(Transcription.Properties.apply)

  // Chords and phrases
  val repeat = P("x" ~ digits)
  val elision = P("->").!

  val phraseannotation = P(repeat | elision)

  val chordstring = P((chordinternals | pause).rep(sep = " ")) map Transcription.Bar.apply

  val chordlist = P("| " ~! chordstring.rep(sep = " | " ~ !phraseannotation) ~ " |" ~ " ".?) map Transcription.Phrase.apply


  //run
  //val chordlists = P(chordlist.rep(1))

  // TODO re-add annotation support...
  val event = P(functionword | chordlist ~ &(phraseannotation.?) | instrument)


  val phrase = P(fractional ~ whitespace ~ event.rep(1, sep = ", ") ~ whitespace) map {
    // cut out just chords for now
    p => val chords = p._4
      println(chords)
      chords
  }

  // Top-level parser
  val transcription = P(properties.! ~! whitespace ~! (phrase | comment).!.rep(1)) // map(_._2 foreach println)
}
