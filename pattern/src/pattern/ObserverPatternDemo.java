package pattern;



//import java.util.ArrayList;
//import java.util.List;
//
//// Subject class
//class Subject {
//   private List<Observer> observers = new ArrayList<Observer>();
//   private int state;
//
//   public int getState() {
//      return state;
//   }
//
//   public void setState(int state) {
//      this.state = state;
//      notifyAllObservers();
//   }
//
//   public void attach(Observer observer){
//      observers.add(observer);		
//   }
//
//   public void detach(Observer observer){
//      observers.remove(observer);		
//   }
//
//   public void notifyAllObservers(){
//      for (Observer observer : observers) {
//         observer.update();
//      }
//   } 	
//}
//
//// Observer class
//abstract class Observer {
//   protected Subject subject;
//   public abstract void update();
//}
//
//// BinaryObserver class
//class BinaryObserver extends Observer{
//
//   public BinaryObserver(Subject subject){
//      this.subject = subject;
//      this.subject.attach(this);
//   }
//
//   @Override
//   public void update() {
//      System.out.println( "Binary String: " + Integer.toBinaryString( subject.getState() ) ); 
//   }
//}
//
//// OctalObserver class
//class OctalObserver extends Observer{
//
//   public OctalObserver(Subject subject){
//      this.subject = subject;
//      this.subject.attach(this);
//   }
//
//   @Override
//   public void update() {
//     System.out.println( "Octal String: " + Integer.toOctalString( subject.getState() ) ); 
//   }
//}
//
//// HexaObserver class
//class HexaObserver extends Observer{
//
//   public HexaObserver(Subject subject){
//      this.subject = subject;
//      this.subject.attach(this);
//   }
//
//   @Override
//   public void update() {
//      System.out.println( "Hex String: " + Integer.toHexString( subject.getState() ).toUpperCase() ); 
//   }
//}
//
//// ObserverPatternDemo class
//public class ObserverPatternDemo {
//   public static void main(String[] args) {
//      Subject subject = new Subject();
//
//      Observer hexaObserver = new HexaObserver(subject);
//      Observer octalObserver = new OctalObserver(subject);
//      Observer binaryObserver = new BinaryObserver(subject);
//
//      System.out.println("First state change: 15");	
//      subject.setState(15);
//
//      // Detach binary observer
//      subject.detach(binaryObserver);
//
//      System.out.println("Second state change: 10");	
//      subject.setState(10);
//   }
//}

import java.util.ArrayList;
import java.util.List;

// Subject class
class Subject {
   private List<Observer> observers = new ArrayList<>();
   private int state;

   public int getState() {
      return state;
   }

   public void setState(int state) {
      this.state = state;
      notifyAllObservers();
   }

   public void attach(Observer observer) {
      observers.add(observer);		
   }

   public void detach(Observer observer) {
      observers.remove(observer);		
   }

   public void notifyAllObservers() {
      for (Observer observer : observers) {
         if (observer.isActive()) { // Notify only active observers
            observer.update();
         }
      }
   } 	
}

// Observer class
abstract class Observer {
   protected Subject subject;
   private boolean active = true; // Status of the observer

   public abstract void update();

   public boolean isActive() {
      return active;
   }

   public void setActive(boolean active) {
      this.active = active;
   }
}

// BinaryObserver class
class BinaryObserver extends Observer {

   public BinaryObserver(Subject subject) {
      this.subject = subject;
      this.subject.attach(this);
   }

   @Override
   public void update() {
      System.out.println("Binary String: " + Integer.toBinaryString(subject.getState())); 
   }
}

// OctalObserver class
class OctalObserver extends Observer {

   public OctalObserver(Subject subject) {
      this.subject = subject;
      this.subject.attach(this);
   }

   @Override
   public void update() {
      System.out.println("Octal String: " + Integer.toOctalString(subject.getState())); 
   }
}

// HexaObserver class
class HexaObserver extends Observer {

   public HexaObserver(Subject subject) {
      this.subject = subject;
      this.subject.attach(this);
   }

   @Override
   public void update() {
      System.out.println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase()); 
   }
}

// ObserverPatternDemo class
public class ObserverPatternDemo {
   public static void main(String[] args) {
      Subject subject = new Subject();

      Observer hexaObserver = new HexaObserver(subject);
      Observer octalObserver = new OctalObserver(subject);
      Observer binaryObserver = new BinaryObserver(subject);

      System.out.println("First state change: 15");	
      subject.setState(15); // Notify all active observers

      // Deactivate binary observer
      binaryObserver.setActive(false); 
      System.out.println("BinaryObserver deactivated.");

      System.out.println("Second state change: 10");	
      subject.setState(10); // Notify only active observers

      // Reactivate binary observer
      binaryObserver.setActive(true);
      System.out.println("BinaryObserver reactivated.");

      System.out.println("Third state change: 5");	
      subject.setState(5); // Notify all active observers again
   }
}