package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.io;

import lombok.RequiredArgsConstructor;

import java.io.PrintStream;
import java.util.Scanner;

@RequiredArgsConstructor
public final class ConsoleIO {
    private final Scanner scanner;
    private final PrintStream out;

    // â”€â”€ input â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

    public String readRequired(final String prompt) {
        String value;
        do {
            out.print(prompt);
            value = scanner.nextLine().trim();
            if (value.isBlank()) {
                out.println("  Valor no puede estar vacío. Por favor intente nuevamente.");
            }
        } while (value.isBlank());
        return value;
    }

    public String readOptional(final String prompt) {
        out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int readInt(final String prompt) {
        while (true) {
            out.print(prompt);
            final String raw = scanner.nextLine().trim();
            try {
                return Integer.parseInt(raw);
            } catch (final NumberFormatException ignored) {
                out.println("  Respuesta invalida. Por favor ingrese un numero entero.");
            }
        }
    }

    // â”€â”€ output â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

    public void println(final String message) {
        out.println(message);
    }

    public void println() {
        out.println();
    }

    public void printf(final String format, final Object... args) {
        out.printf(format, args);
    }

}
