package hellojpa;

import org.hibernate.annotations.common.reflection.XMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> results = findMember.getTeam().getMembers();
            for (Member result : results) {
                System.out.println("리스트 멤버 목록 : " + result.getUsername());
            }

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }


        emf.close();


    }

}
