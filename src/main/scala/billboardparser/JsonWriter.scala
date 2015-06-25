/**
 * Created by Dakota on 6/24/2015.
 */
package billboardparser

import spray.json._
import BBJsonProtocol._

object JsonWriter {
  def write(t: Transcription.Song) = t.toJson
}