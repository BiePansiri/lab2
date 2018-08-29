import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class XOTest {

    @Test
    public void shouldCreateOXObjectCorrectly() {


        OX ox = new OX();
        // " 0 1 2\n 0 - - -\n 1 - - -\n 2 - - -\n"
        ox.getTableString();
        assertEquals(" 012\n0---\n1---\n2---\n",ox.getTableString());
        assertEquals("X",ox.getCurrentPlayer());
        assertEquals(0,ox.getCountO());
        assertEquals(0,ox.getCountX());
        assertEquals(0,ox.getCountDraw());

    }

    @Test
    public void put() {
        OX ox = new OX();

        ox.put(1,1);
        assertEquals(" 012\n0---\n1-X-\n2---\n",ox.getTableString());

        ox.put(0,0);
        assertEquals(" 012\n0X--\n1-X-\n2---\n",ox.getTableString());

        ox.put(2,1);
        assertEquals(" 012\n0X--\n1-XX\n2---\n",ox.getTableString());

        ox.swicthPlayer();
        ox.put(0,2);
        assertEquals(" 012\n0X--\n1-XX\n2O--\n",ox.getTableString());

        assertEquals(false, ox.put(0,2));

        assertEquals(false, ox.put(0,3));
    }

    @Test
    void getOver() {
        OX ox = new OX();

        assertNull(ox.get(0,-1));
        assertNull(ox.get(0,3));
        assertNull(ox.get(3,-1));
        assertNull(ox.get(-1,3));
    }

    @Test
    public void switchPlayer() {
        OX ox = new OX();
        ox.swicthPlayer();
        assertEquals("O",ox.getCurrentPlayer());
        ox.swicthPlayer();
        assertEquals("X",ox.getCurrentPlayer());
    }

   @Test
    void checkWinCol0() {
        OX ox = new OX();
        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertTrue(ox.checkWin(0,0));
        assertTrue(ox.checkWin(0,1));
        assertTrue(ox.checkWin(0,2));
        assertFalse(ox.checkWin(1,0));
        assertFalse(ox.checkWin(1,1));
        assertFalse(ox.checkWin(1,2));
    }

    @Test
    void checkWinCol2() {
        OX ox = new OX();
        ox.put(2,0);
        ox.put(2,1);
        ox.put(2,2);
        assertTrue(ox.checkWin(2,0));
        assertTrue(ox.checkWin(2,1));
        assertTrue(ox.checkWin(2,2));
        assertFalse(ox.checkWin(1,0));
        assertFalse(ox.checkWin(1,1));
        assertFalse(ox.checkWin(1,2));
    }

    @Test
    void checkWinRow2() {
        OX ox = new OX();
        ox.put(0,2);
        ox.put(1,2);
        ox.put(2,2);
        assertTrue(ox.checkWin(0,2));
        assertTrue(ox.checkWin(1,2));
        assertTrue(ox.checkWin(2,2));
        assertFalse(ox.checkWin(1,0));
        assertFalse(ox.checkWin(1,1));
        assertFalse(ox.checkWin(2,1));
    }

    @Test
    void checkWinES() {
        OX ox = new OX();
        ox.put(0,0);
        ox.put(1,1);
        ox.put(2,2);
        assertTrue(ox.checkWin(0,0));
        assertTrue(ox.checkWin(1,1));
        assertTrue(ox.checkWin(2,2));

    }

    @Test
    void checkWinSS() {
        OX ox = new OX();
        ox.put(2,0);
        ox.put(1,1);
        ox.put(0,2);
        assertTrue(ox.checkWin(2,0));
        assertTrue(ox.checkWin(1,1));
        assertTrue(ox.checkWin(0,2));
    }

    @Test
    void reset(){
        OX ox = new OX();
        ox.put(2,0);
        ox.put(1,1);
        ox.put(0,2);

        ox.reset();
        assertEquals(" 012\n0---\n1---\n2---\n",ox.getTableString());
        assertEquals("X",ox.getCurrentPlayer());
        assertEquals(0,ox.getTurnCount());

    }

    @Test
    void getTurn(){
        OX ox = new OX();
        assertEquals(0,ox.getTurnCount());
        ox.put(0,0);
        assertEquals(1,ox.getTurnCount());
    }

    @Test
    void isDraw(){
        OX ox = new OX();

        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertFalse(ox.isDraw());

        ox.put(1,0);
        ox.put(1,1);
        ox.put(1,2);
        assertFalse(ox.isDraw());

        ox.put(2,0);
        ox.put(2,1);
        ox.put(2,2);

        assertTrue(ox.isDraw());
    }

    @Test
    void getScoreX(){
        OX ox = new OX();
        assertEquals(0,ox.getScoreX());
        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertEquals(1,ox.getScoreX());
    }

    @Test
    void getScoreO(){
        OX ox = new OX();
        ox.swicthPlayer();
        assertEquals(0,ox.getScoreO());
        ox.put(0,0);
        ox.put(1,1);
        ox.put(2,2);
        assertEquals(1,ox.getScoreO());
    }


    @Test
    void getScoreDraw(){
        OX ox = new OX();

        assertEquals(0,ox.getScoreDraw());
        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertFalse(ox.isDraw());

        ox.put(1,0);
        ox.put(1,1);
        ox.put(1,2);
        assertFalse(ox.isDraw());

        ox.put(2,0);
        ox.put(2,1);
        ox.put(2,2);
        assertEquals(1,ox.getScoreDraw());
    }
}