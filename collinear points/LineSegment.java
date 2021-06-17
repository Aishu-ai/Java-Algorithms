public class LineSegment{
	private final Point p1;
	private final Point p2;
	public LineSegment(Point p1,Point p2){
		this.p1=p1;
		this.p2=p2;
	}
	public void printls(){
		System.out.println("("+p1.getx()+", "+p1.gety()+")-->("+p2.getx()+", "+p2.gety()+")");
	}	
		
}		
