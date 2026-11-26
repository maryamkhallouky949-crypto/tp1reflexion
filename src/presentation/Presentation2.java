
package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation2 {

    public static void main(String[] args) throws Exception {

        File fichier = new File("config.txt");

        System.out.println("Chemin : " + fichier.getAbsolutePath());
        System.out.println("Fichier existe : " + fichier.exists());

        Scanner scanner = new Scanner(fichier);

        String daoClassName = scanner.nextLine().trim();
        String metierClassName = scanner.nextLine().trim();

        System.out.println("DAO : [" + daoClassName + "]");
        System.out.println("Metier : [" + metierClassName + "]");

        Class<?> cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

        Class<?> cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();

        Method method = cMetier.getMethod("setDao", IDao.class);
        method.invoke(metier, dao);

        System.out.println("Résultats = " + metier.calcul());

        scanner.close();
    }
}
