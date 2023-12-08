package edu.project4_fractal_flame;

@SuppressWarnings("MagicNumber")
public class FractalFlameSession {


    FractalFlameSession() {

    }

    public void run() {
        userInputs();

        FractalFlame fractalFlame = new FractalFlame(1920, 1080);
        fractalFlame.gammaCorrection();

        GraphicUserInterface gui = new GraphicUserInterface();
        gui.runFractalFlameWindow();
    }

    private void userInputs() {

    }
}
