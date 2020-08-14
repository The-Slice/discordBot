/**
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authors: Franck Fleurey and Brice Morin
 * Company: SINTEF IKT, Oslo, Norway
 * Date: 2011
 */
package discordBot_maven.discordBotMaven;

import org.sintef.jarduino.*;

public class ArduinoInterface extends JArduino implements Pitches {
	public static JArduino arduino;
	short melody[] = { NOTE_C4 };
	int noteDurations[] = { 10 };

	public static void main(String[] args) {
		arduino = new ArduinoInterface();
		arduino.runArduinoProcess();

	}

	public ArduinoInterface() {
		super("COM3");
	}

	protected void setup() {
		// initialize the digital pin as an output.
		pinMode(DigitalPin.PIN_6, PinMode.OUTPUT);

	}

	protected void loop() {
		
		/*
		 * while (i < 3) { digitalWrite(DigitalPin.PIN_6, DigitalState.LOW); // to
		 * calculate the note duration, take one second // divided by the note type. //
		 * e.g. quarter note = 1000 / 4, eighth note = 1000/8, etc. short noteDuration =
		 * (short) (10000 / noteDurations[0]); tone(DigitalPin.PIN_12, melody[0],
		 * noteDuration); digitalWrite(DigitalPin.PIN_6, DigitalState.HIGH);
		 * 
		 * // to distinguish the notes, set a minimum time between them. // the note's
		 * duration + 30% seems to work well: int pauseBetweenNotes = (int)
		 * (noteDuration * 1.30); delay(pauseBetweenNotes); i++; }
		 * 
		 * digitalWrite(DigitalPin.PIN_6, DigitalState.LOW);
		 */

	}

	protected void runOnce() {

		int j = 0;

		while (j < 3) {
			digitalWrite(DigitalPin.PIN_6, DigitalState.LOW);
			// to calculate the note duration, take one second
			// divided by the note type.
			// e.g. quarter note = 1000 / 4, eighth note = 1000/8, etc.
			short noteDuration = (short) (10000 / noteDurations[0]);
			tone(DigitalPin.PIN_12, melody[0], noteDuration);
			digitalWrite(DigitalPin.PIN_6, DigitalState.HIGH);

			// to distinguish the notes, set a minimum time between them.
			// the note's duration + 30% seems to work well:
			int pauseBetweenNotes = (int) (noteDuration * 1.30);
			delay(pauseBetweenNotes);
			j++;
		}
		digitalWrite(DigitalPin.PIN_6, DigitalState.LOW);

	}

}
