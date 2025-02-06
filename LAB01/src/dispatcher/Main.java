/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatcher;

import business.Mountains;
import business.Students;
import tools.Menu;

/**
 *
 * @author hieuh
 */
public class Main {

    public static void main(String[] args) {
        Mountains mt = new Mountains();
        Students sts = new Students();
        sts.readFromFile();
        mt.readFromFile();
        Menu menu = new Menu();
        menu.showMenu();
    }
}
