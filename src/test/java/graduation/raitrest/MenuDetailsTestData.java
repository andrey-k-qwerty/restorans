package graduation.raitrest;

import graduation.raitrest.model.entities.MenuDetails;

import java.util.Date;

import  static graduation.raitrest.RestoranTestData.*;
import  static graduation.raitrest.UserTestData.*;
public class MenuDetailsTestData {
    public static final int MENU_DETAILS_ID = 100;
    public static final MenuDetails MENU_DETAILS_PEARL = new MenuDetails(MENU_DETAILS_ID, "1. meal dish first",
           new Date(),  RESTORAN_PEARL,ADMIN);
}
