package billboardparser

import spray.json._

/**
 * Created by Dakota on 6/24/2015.
 */
object BBJsonProtocol extends DefaultJsonProtocol {
  implicit val chordFormat = jsonFormat2(Transcription.Chord)
  implicit val barFormat = jsonFormat1(Transcription.Bar)
  implicit val numberedBarFormat = jsonFormat2(Transcription.NumberedBar)
  implicit val phraseFormat = jsonFormat1(Transcription.Phrase)
  implicit val annotationFormat = jsonFormat1(Transcription.Annotation)

  implicit val eventFormat = new RootJsonFormat[Transcription.Event] {
    def write(obj: Transcription.Event) = obj match {
      case x: Transcription.Phrase => phraseFormat.write(x)
      case x: Transcription.Chord => chordFormat.write(x)
      case x: Transcription.Bar => barFormat.write(x)
      case x: Transcription.Annotation => annotationFormat.write(x)
    }

    override def read(value: JsValue) = Transcription.Annotation("")
  }
  implicit val songFormat = jsonFormat1(Transcription.Song)
  // TODO add prop support
  // implicit val propsFormat = jsonFormat4(Transcription.Properties)
}
