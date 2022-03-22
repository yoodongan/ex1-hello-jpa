package hellojpa;

import org.hibernate.annotations.common.reflection.XMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.awt.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("A");
            member.setHomeAddress(new Address("city", "street","100"));
            member.setWorkPeriod(new Period());
            member.setWorkAddress(new Address("workcity", "workstreet", "20000"));

            em.persist(member);

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }


        emf.close();


    }

    private static void logic(Member findMember1, Member findMember2) {
        System.out.println("(findMember1 instanceof Member = " + (findMember1 instanceof Member));
        System.out.println("(findMember1 instanceof Member = " + (findMember2 instanceof Member));

    }

    private static void printMember(Member member) {
        System.out.println("member = " + member.getUsername());
        
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team.getName() = " + team.getName());
    }

}
