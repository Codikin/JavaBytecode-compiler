package segment;
import segment.array2DSegment.array2DSegment;
import segment.arraySegment.arraySegment;
import segment.forLoopSegment.forLoopSegment;
import segment.ifElseSegment.ifElseSegment;
import segment.localVarSegment.localVarSegment;
import segment.mainClassSegment.mainClassSegment;
import segment.whileLoopSegment.whileLoopSegment;

public class CentralStorage {
    private static CentralStorage instance;
    private mainClassSegment mainClassSegment;
    private whileLoopSegment whileLoopSegment;
    private localVarSegment localVarSegment;
    private forLoopSegment forLoopSegment;
    private ifElseSegment ifElseSegment;
    private array2DSegment array2DSegment;
    private arraySegment arraySegment;

    public array2DSegment getArray2DSegment() {
        return array2DSegment;
    }

    public void setArray2DSegment(array2DSegment array2DSegment) {
        this.array2DSegment = array2DSegment;
    }



    public segment.arraySegment.arraySegment getArraySegment() {
        return arraySegment;
    }

    public void setArraySegment(segment.arraySegment.arraySegment arraySegment) {
        this.arraySegment = arraySegment;
    }

    public ifElseSegment getIfElseSegment() {
        return ifElseSegment;
    }

    public void setIfElseSegment(ifElseSegment ifElseSegment) {
        this.ifElseSegment = ifElseSegment;
    }

    public segment.forLoopSegment.forLoopSegment getForLoopSegment() {
        return forLoopSegment;
    }

    public void setForLoopSegment(segment.forLoopSegment.forLoopSegment forLoopSegment) {
        this.forLoopSegment = forLoopSegment;
    }
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
