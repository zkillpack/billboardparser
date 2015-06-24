/**
 * Created by Dakota on 6/24/2015.
 */
package billboardparser

object Transcription {

  abstract class MusicPrimitive

  case class Title(title: String) extends MusicPrimitive

  case class Artist(artist: String) extends MusicPrimitive

  case class Meter(meter: String) extends MusicPrimitive

  case class Key(key: String) extends MusicPrimitive

  case class Chord(chord: String, beat: Int) extends MusicPrimitive

  case class Bar(chords: Seq[Chord]) extends MusicPrimitive

  case class NumberedBar(chords: Seq[Chord], beat: Int)

  case class Phrase(bars: Seq[Bar]) extends MusicPrimitive

  case class Properties(title: Title, artist: Artist, meter: Meter, key: Key) extends MusicPrimitive

  //
  //  object Properties {
  //    // Because music grad students can't annotate in order sometimes
  //    def apply(props: Product4[String, String, String, String]): Properties = {
  //      props._3 match{
  //        case Key(k) => Properties(Title(props._1), Artist(props._1), Meter(props._4), Key(props._3))
  //        case Meter(m) => Properties(Title(props._1), Artist(props._2),Meter(props._3), Key(props._4))
  //      }
  //    }
  //  }

}