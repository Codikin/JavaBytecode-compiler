package segment;
import segment.mainClassSegment.mainClassSegment;
import segment.whileLoopSegment.whileLoopSegment;

public class CentralStorage {
    private static CentralStorage instance;
    private mainClassSegment mainClassSegment;
    private whileLoopSegment whileLoopSegment;

    public void setMainClassSegment(segment.mainClassSegment.mainClassSegment mainClassSegment) {
        this.mainClassSegment = mainClassSegment;
    }

    public whileLoopSegment getWhileLoopSegment() {
        return whileLoopSegment;
    }

    public void setWhileLoopSegment(whileLoopSegment whileLoopSegment) {
        this.whileLoopSegment = whileLoopSegment;
    }
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
