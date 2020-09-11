package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CursaTest {
    Cursa cursa = new Cursa("250",2);


    @Test
    public void getCapCilindrica() {
        assertEquals("250",cursa.getCapCilindrica());
    }

    @Test
    public void setCapCilindrica() {
        cursa.setCapCilindrica("100");
        assertEquals("100",cursa.getCapCilindrica());
    }

    @Test
    public void getNumarParticipanti() {
    }

    @Test
    public void setNumarParticipanti() {
    }

    @Test
    public void incrementParticipanti() {
    }

    @Test
    public void getId() {
    }

    @Test
    public void setId() {
    }

    @Test
    public void testToString() {
    }
}