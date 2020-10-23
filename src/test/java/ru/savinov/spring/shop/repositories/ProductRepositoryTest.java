package ru.savinov.spring.shop.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.savinov.spring.shop.entities.Product;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest extends AbstractJpaRepositoryTest<Product> {
    Product product;

    @Resource
    private ProductRepository subject;

    @BeforeEach
    public void initTest(){
 //       product = new Product(1L, "ProductTest", 99);

    }

    @Test
    public void findById() {
        Long id = (Long) em.persistAndGetId(new Product(1L, "ProductTest", 99));
        em.flush();
        em.clear();
        assertTrue(subject.findById(id).isPresent());
    }


  /* class RegExpLevelRepositoryTest extends AbstractJpaRepositoryTest<RegExpLevel> {

  @Resource
  private RegExpLevelRepository subject;

  private RegExpLevel level;

  @BeforeEach
  public void initTest() {
    level = getRegExpLevel();
  }

  @Test
  public void findById() {
    Integer id = (Integer) em.persistAndGetId(level);
    em.flush();
    em.clear();
    assertTrue(subject.findById(id).isPresent());
  }

  @Test
  public void findAll() {
    em.persist(getRegExpLevel());
    em.persist(getRegExpLevel());
    em.persist(getRegExpLevel());
    em.flush();
    em.clear();
    List<RegExpLevel> all = subject.findAll();
    assertEquals(3, all.size());
  }

  @Test
  public void update() {
    em.persistAndFlush(level);
    em.clear();
    level.setScore(80);
    RegExpLevel actual = subject.saveAndFlush(level);
    assertEquals(80, actual.getScore());
  }

  @Test
  public void delete() {
    Integer id = (Integer) em.persistAndGetId(level);
    em.flush();
    em.clear();
    subject.deleteById(id);
    RegExpLevel actual = em.find(RegExpLevel.class, id);
    assertNull(actual);
  }

  @Test
  public void create() {
    RegExpLevel regExpLevel = subject.saveAndFlush(level);
    RegExpLevel actual = em.find(RegExpLevel.class, regExpLevel.getId());
    assertNotNull(actual);
  }

}
*/
    }