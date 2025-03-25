package it.unibo.oops;

import it.unibo.oops.controller.GameThreadImpl;

final class MainApp {
    private MainApp() {
    }
    public static void main(final String[] args) {
        new GameThreadImpl();
    }
}
