package segment;
import segment.mainClassSegment.mainClassSegment;
public class CentralStorage {
    private static CentralStorage instance;

    public void setMainClassSegment(segment.mainClassSegment.mainClassSegment mainClassSegment) {
        this.mainClassSegment = mainClassSegment;
    }

    private mainClassSegment mainClassSegment;
    private CentralStorage() {

    }
    public static CentralStorage getInstance() {
        if (instance == null) {
            instance = new CentralStorage();
        }
        return instance;
    }
    public mainClassSegment getMainClassSegment() {
        return mainClassSegment;
    }
}
