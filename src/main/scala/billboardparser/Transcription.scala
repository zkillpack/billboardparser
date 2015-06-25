/**
 * Created by Dakota on 6/24/2015.
 */
package billboardparser

object Transcription {


  sealed trait Event

  sealed trait Description extends Event

  case class Title(title: String) extends Description

  case class Artist(artist: String) extends Description

  case class Meter(meter: String) extends Description

  case class Key(key: String) extends Description

  case class Chord(chord: String, beat: Int) extends Event

  case class Annotation(annotationText: String) extends Event

  case class Bar(chords: Seq[Chord]) extends Event

  case class NumberedBar(chords: Seq[Chord], beat: Int)

  case class Phrase(bars: Seq[Bar]) extends Event

  case class Properties(title: Title, artist: Artist, meter: Meter, key: Key) extends Description

  case class Song(events: Seq[Seq[Event]])

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