package scaperoom;

public class ScapeNode {

    private ScapeNode parent;

    int col = 0;
    int row = 0;
    int gcost = 0;
    int hcost = 0;
    int fcost = 0;

    private String value;

    private boolean open;
    private boolean closed;
    private boolean isSolid;
    private boolean isPath;
    private boolean isGoal;
    private boolean isStart;
    private boolean isChecked;
    private boolean isBorder;

    public ScapeNode() {}
    public ScapeNode(String type, int col, int row, int maxrow, int maxcol) {

        switch (type) {
            case "open":
                open = true;
                break;
            case "close":
                closed = true;
                break;
            case "S":
                this.value = "S";
                this.isStart = true;
                break;
            case "X":
                this.value = "X";
                this.isSolid = true;
                break;
            case "_":
                this.value = "_";
                this.isPath = true;
                break;
            case "E":
                this.value = "E";
                this.isGoal = true;
                break;
            case "checked":
                this.isChecked = true;
                break;
            default:
                break;
        }

        this.col = col;
        this.row = row;

        if(isSolid && ( col == 0 || col == maxcol || row == 0 || row == maxrow ))
            this.isBorder = true;
    }

    public void openNode(){
        this.open = true;
    }

    public void closeNode(){
        this.closed = true;
    }

    public void checkNode(){
        this.isChecked = true;
    }


    public ScapeNode getParent() {
        return parent;
    }

    public void setParent(ScapeNode parent) {
        this.parent = parent;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean solid) {
        isSolid = solid;
    }

    public boolean isPath() {
        return isPath;
    }

    public void setPath(boolean path) {
        isPath = path;
    }

    public boolean isGoal() {
        return isGoal;
    }

    public void setGoal(boolean goal) {
        isGoal = goal;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean isBorder() {
        return isBorder;
    }

    public void setBorder(boolean border) {
        isBorder = border;
    }
}
