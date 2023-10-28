package segment.mainClassSegment;

public class mainClassSegment {
    public mainClassSegment() {
    }
    public mainClassSegment(String mainClassName, String printValue) {
        this.mainClassName = mainClassName;
        this.printValue = printValue;
    }
    String mainClassName;
    String printValue;

    public String getMainClassName() {
        return mainClassName;
    }

    public void setMainClassName(String mainClassName) {
        this.mainClassName = mainClassName;
    }

    public String getPrintValue() {
        return printValue;
    }

    public void setPrintValue(String printValue) {
        this.printValue = printValue;
    }
}
