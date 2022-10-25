package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { //item은 JPA에 저장하기 전까지 ID값이 없다. -> 새로 생성하는 객체라는 뜻
            em.persist(item);
        } else {
            em.merge(item); //Id가 있으면 이미 DB에 있는 상품 이므로 업데이트(실제로 merge는 잘 사용하지 않는다)
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
