package edu.project4_fractal_flame;

public class FractalFlameSession {


    FractalFlameSession() {

    }

    public void run() {
        userInputs();

        FractalFlame fractalFlame = new FractalFlame();
        fractalFlame.generateFlame();
        fractalFlame.gammaCorrection();

        GraphicUserInterface gui = new GraphicUserInterface();
        gui.runFractalFlameWindow();
    }

    private void userInputs() {

    }
}
