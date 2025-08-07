package utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.android.AndroidDriver;

public class Gestures {

    public static final PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

    private static AndroidDriver getDriver() {
        return new DriverProvider().get();
    }

    public static void tap(WebElement element) {
        final var centerPoint = getCenterPoint(element);
        final var sequence = new Sequence(finger,   1 );
        
        Logs.debug("Moviendo el dedo hacia el elemento");
        sequence.addAction(finger.createPointerMove(
            Duration.ofMillis(1000),
            PointerInput.Origin.viewport(),
            centerPoint.getX(),
            centerPoint.getY()
        ));

        Logs.debug("Presioando el elemento");
        sequence.addAction(finger.createPointerDown(
            PointerInput.MouseButton.LEFT.asArg()
        ));

        Logs.debug("Esperando 1 segundo");
        sequence.addAction(new Pause(finger,Duration.ofMillis(1000)));

        Logs.debug("Levantando el dedo del elemento");
        sequence.addAction(finger.createPointerUp(
            PointerInput.MouseButton.LEFT.asArg()
        ));

        Logs.debug("Ejecutando las acciones "); 
        getDriver().perform(List.of(sequence));

    }

    public static void longTap(WebElement element) {
        final var centerPoint = getCenterPoint(element);
        final var sequence = new Sequence(finger, 1);

        Logs.debug("Moviendo el dedo hacia el elemento");
        sequence.addAction(finger.createPointerMove(
            Duration.ofMillis(1000),
            PointerInput.Origin.viewport(),
            centerPoint.getX(),
            centerPoint.getY()
        ));

        Logs.debug("Presionando el elemento");
        sequence.addAction(finger.createPointerDown(
            PointerInput.MouseButton.LEFT.asArg()
        ));

        Logs.debug("Esperando 3.5 segundos");
        sequence.addAction(new Pause(finger, Duration.ofMillis(3500)));

        Logs.debug("Levantando el dedo del elemento");
        sequence.addAction(finger.createPointerUp(
            PointerInput.MouseButton.LEFT.asArg()
        ));

        Logs.debug("Ejecutando las acciones");
        getDriver().perform(List.of(sequence));
    }

    public static void doubleTap(WebElement element) {
        final var centerPoint = getCenterPoint(element);
        final var sequence = new Sequence(finger, 1);

        Logs.debug("Moviendo el dedo hacia el elemento");
        sequence.addAction(finger.createPointerMove(
            Duration.ofMillis(1000),
            PointerInput.Origin.viewport(),
            centerPoint.getX(),
            centerPoint.getY()
        ));

        Logs.debug("Presionando el elemento");
        sequence.addAction(finger.createPointerDown(
            PointerInput.MouseButton.LEFT.asArg()
        ));

        Logs.debug("Esperando 500 milisegundos");
        sequence.addAction(new Pause(finger, Duration.ofMillis(500)));

        Logs.debug("Levantando el dedo del elemento");
        sequence.addAction(finger.createPointerUp(
            PointerInput.MouseButton.LEFT.asArg()
        ));

        Logs.debug("Presionando nuevamente el elemento");
        sequence.addAction(finger.createPointerDown(
            PointerInput.MouseButton.LEFT.asArg()
        ));

        Logs.debug("Esperando 500 milisegundos");
        sequence.addAction(new Pause(finger, Duration.ofMillis(500)));

        Logs.debug("Levantando el dedo del elemento nuevamente");
        sequence.addAction(finger.createPointerUp(
            PointerInput.MouseButton.LEFT.asArg()
        ));

        Logs.debug("Ejecutando las acciones");
        getDriver().perform(List.of(sequence));
    }

    public static void dragTo(WebElement source, WebElement target) {
        final var sourcePoint = getCenterPoint(source);
        final var targetPoint = getCenterPoint(target);
        final var sequence = new Sequence(finger, 1);

        Logs.debug("Moviendo el dedo hacia el elemento fuente");
        sequence.addAction(finger.createPointerMove(
            Duration.ofMillis(1000),
            PointerInput.Origin.viewport(),
            sourcePoint
        ));

        Logs.debug("Presionando el elemento fuente");
        sequence.addAction(finger.createPointerDown(
            PointerInput.MouseButton.LEFT.asArg()
        ));

        Logs.debug("Esperando 2 segundo");
        sequence.addAction(new Pause(finger, Duration.ofMillis(2000)));

        Logs.debug("Moviendo el dedo hacia el elemento destino");
        sequence.addAction(finger.createPointerMove(
            Duration.ofMillis(1000),
            PointerInput.Origin.viewport(),
            targetPoint
        ));

        Logs.debug("Esperando 1 segundo");
        sequence.addAction(new Pause(finger, Duration.ofMillis(1000)));

        Logs.debug("Levantando el dedo del elemento destino");
        sequence.addAction(finger.createPointerUp(
            PointerInput.MouseButton.LEFT.asArg()
        ));

        Logs.debug("Ejecutando las acciones");
        getDriver().perform(List.of(sequence));
    }

    private static Point getCenterPoint(WebElement element) {
        final var location = element.getLocation();
        final var size = element.getSize();

        final var x = location.getX() + (size.getWidth() / 2);
        final var y = location.getY() + (size.getHeight() / 2); 

        return new Point(x, y);
       
    }
}
