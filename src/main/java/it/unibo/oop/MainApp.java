package it.unibo.oop;

import it.unibo.oop.controller.GameThreadImpl;

final class MainApp {
    private MainApp() {
    }
    public static void main(final String[] args) {
        new GameThreadImpl();
    }
}
