package domein;

public class LaatsteRonde {
    private boolean laatsteRonde;

    public LaatsteRonde() {
        this.laatsteRonde = false;
    }

    public void startLaatsteRonde() {
        this.laatsteRonde = true;
    }

    public boolean isLaatsteRonde() {
        return laatsteRonde;
    }
}