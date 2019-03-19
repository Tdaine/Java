package LinkList;



public class ReviewLinkList {

    public void printFromTailToFront(LinkImpl.Node node){
        LinkImpl.Node temp;
        temp = node;
        if(temp != null){
            printFromTailToFront(temp.getNext());
            System.out.println(temp.getData());
        }
    }
}
