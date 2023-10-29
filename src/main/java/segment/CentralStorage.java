package segment;
import segment.forLoopSegment.forLoopSegment;
import segment.localVarSegment.localVarSegment;
import segment.mainClassSegment.mainClassSegment;
import segment.whileLoopSegment.whileLoopSegment;

public class CentralStorage {
    private static CentralStorage instance;
    private mainClassSegment mainClassSegment;
    private whileLoopSegment whileLoopSegment;
    private localVarSegment localVarSegment;

    public segment.forLoopSegment.forLoopSegment getForLoopSegment() {
        return forLoopSegment;
    }

    public void setForLoopSegment(segment.forLoopSegment.forLoopSegment forLoopSegment) {
        this.forLoopSegment = forLoopSegment;
    }

    private forLoopSegment forLoopSegment;

    public localVarSegment getLocalVarSegment() {
        return localVarSegment;
    }

    public void setLocalVarSegment(localVarSegment localVarSegment) {
        this.localVarSegment = localVarSegment;
    }


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
