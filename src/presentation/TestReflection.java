package presentation;

import dao.IDao;
import metier.IMetier;

import java.lang.reflect.Method;

public class TestReflection {

    public static void main(String[] args) throws Exception {

        String[] daoClasses = {
            "dao.DaoImpl",
            "dao.DaoImpl2",
            "dao.DaoImpl3",
            "dao.DaoImpl4"
        };

        String metierClass = "metier.MetierImpl";

        for (String daoName : daoClasses) {

            // Création du DAO par réflexion
            Class<?> cDao = Class.forName(daoName);
            IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

            // Création du métier par réflexion
            Class<?> cMetier = Class.forName(metierClass);
            IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();

            // Injection du DAO
            Method method = cMetier.getMethod("setDao", IDao.class);
            method.invoke(metier, dao);

            // Calcul
            System.out.println("DAO utilisé : " + daoName);
            System.out.println("Résultat : " + metier.calcul());
            System.out.println("--------------------------");
        }
    }
}