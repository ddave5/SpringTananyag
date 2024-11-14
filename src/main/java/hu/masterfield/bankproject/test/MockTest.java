package hu.masterfield.bankproject.test;

import static org.easymock.EasyMock.*;

public class MockTest {

    public interface Subscriber {
        public String getName();
        public int getAge();
        public int setBalance(int balance);

    }

    public static void main(String[] args) {
        Subscriber subs = createMock(Subscriber.class);

        expect(subs.getName()).andReturn("Kiss Zoltán");
        expect(subs.getAge()).andReturn(87);
        expect(subs.setBalance(100)).andReturn(600).times(1).andThrow(new RuntimeException("Exception occured..."));

        replay(subs);
        System.out.println(subs.getName());
        System.out.println(subs.getAge());
        System.out.println(subs.setBalance(100));

        try {
            System.out.println(subs.setBalance(100));
        } catch( Exception e) {
            e.printStackTrace();
        }

        verify(subs);
        
    }
}

