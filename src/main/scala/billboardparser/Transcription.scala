/**
 * Created by Dakota on 6/24/2015.
 */
package billboardparser

object Transcription {

  // TODO

  case class Title(title: String)

  case class Artist(artist: String)

  case class Meter(meter: String)

  case class Key(key: String)

  case class Chord(chord: String, beat: Int)

  case class Bar(chords: Chord*)

  case class Phrase(bars: Bar*)

  case class Properties(title: Title, artist: Artist, meter: Meter, key: Key)

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