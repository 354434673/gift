package test;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gift.commons.JsonUtil;
import com.gift.commons.Page;
import com.gift.commons.TreeJson;
import com.gift.dao.impl.JedisClientSingle;
import com.gift.mapper.ApplyUserMapper;
import com.gift.mapper.CustomerAndCategoryMapper;
import com.gift.mapper.CustomerLogoFixMapper;
import com.gift.mapper.CustomerMapper;
import com.gift.mapper.GiftItemCategoryMapper;
import com.gift.mapper.GiftItemMapper;
import com.gift.mapper.GiftNewsMapper;
import com.gift.mapper.LogoMapper;
import com.gift.service.CustomerLogoFixService;
import com.gift.vo.ApplyUser;
import com.gift.vo.ApplyUserExample;
import com.gift.vo.Customer;
import com.gift.vo.CustomerAndCategory;
import com.gift.vo.CustomerLogoFix;
import com.gift.vo.CustomerLogoFixExample;
import com.gift.vo.GiftItem;
import com.gift.vo.GiftItemCategory;
import com.gift.vo.GiftItemCategoryExample;
import com.gift.vo.GiftItemExample;
import com.gift.vo.GiftNews;
import com.gift.vo.GiftNewsExample;
import com.gift.vo.Logo;
import com.gift.vo.LogoExample;
import com.gift.vo.LogoExample.Criteria;
import com.github.pagehelper.PageHelper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/applicationContext-*.xml")
public class test {
    @Autowired
    private LogoMapper logoMapper;
    @Autowired
    private GiftItemMapper giftItemMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private GiftItemCategoryMapper giftItemCategoryMapper;
    @Autowired
    private CustomerLogoFixMapper customerLogoFixMapper;
    @Autowired
    private CustomerLogoFixService customerLogoFixService;
    @Autowired
    private CustomerAndCategoryMapper customerAndCategoryMapper;
    @Autowired
    private ApplyUserMapper applyUserMapper;
    @Autowired
    private GiftNewsMapper giftNewsMapper;
    @Autowired
    private JedisClientSingle jedisClientSingle;
	@Autowired
	private JedisPool jedisPool;
    @Test
    public void test1(){
	/**
	 * 
	 * 第二种方式是使用BigDecimal，但一定要用BigDecimal(String)构造器，而千万不要用 BigDecimal(double)来构造
	 * （也不能将float或double型转换成String再来使用BigDecimal(String)来构造，因为在将float或double转换成String时精度已丢失）。 
                                例如new BigDecimal(0.1)， 
                                它将返回一个BigDecimal， 
                                也即0.1000000000000000055511151231257827021181583404541015625， 
                                正确使用BigDecimal，程序就可以打印出我们所期 
	 */
	System.out.println(new BigDecimal("2.0").subtract(new BigDecimal("1.10")));
	/**
	 * >返回1,<返回-1,相同返回0
	 */
	System.out.println(new BigDecimal("1.10").compareTo(new BigDecimal("1.10")));
    }
    @Test
    public void test2(){
	long microsPerDay = 24 * 60 * 60 * 1000 * 1000;// 正确结果应为：86400000000  
	//long microsPerDay = 24L * 60 * 60 * 1000 * 1000;  
	System.out.println(0x80000001);//128 
	
    }
    @Test
    public void test3(){
	
/*	LogoExample logoExample = new LogoExample();

	Criteria createCriteria = logoExample.createCriteria();
	
	List<Logo> selectByExample = logoMapper.selectLogoAndCustomer(logoExample,0L);
	
	for (Logo logo : selectByExample) {
	    System.out.println(logo.getCustomer().getName());
	}*/
	List<Logo> selectLogoAndCustomerByNameOrTitle = logoMapper.selectLogoAndCustomerByNameOrTitle("%未%", 0l);
	
	for (Logo logo : selectLogoAndCustomerByNameOrTitle) {
	    System.out.println(logo.getCustomer().getName());
	}
	
    }
    @Test
    public void test20(){
	List<GiftNews> selectByExampleForTitle = giftNewsMapper.selectByExampleForTitle(5);
	GiftNews giftNews = new GiftNews();
	
	for (GiftNews giftNews2 : selectByExampleForTitle) {
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    
	    String format = simpleDateFormat.format(giftNews2.getUpdated());
	    
	    System.out.println(format);
	}
    }
    @Test
    public void test4(){
	
	GiftItemExample giftItemExample = new GiftItemExample();
	
	com.gift.vo.GiftItemExample.Criteria createCriteria = giftItemExample.createCriteria();
	
	List<GiftItem> selectByExampleByCustomerId = giftItemMapper.selectByExampleByCustomerId(giftItemExample, "5b1309d52a80457a84bc5257d9af9166");
	
	for (GiftItem giftItem : selectByExampleByCustomerId) {
	    System.out.println(giftItem.getPic2());
	}
	
    }
    @Test
    public void test6(){
	
/*	List<CustomerAndCategory> selectAll = customerAndCategoryMapper.selectAll("5b1309d52a80457a84bc5257d9af9166");
	
	for (CustomerAndCategory customerAndCategory : selectAll) {
	    System.out.println(customerAndCategory.getCustomeId());
	}*/
	//customerAndCategoryMapper.addCustomerAndCategory(new CustomerAndCategory("111", "111"));
	CustomerAndCategory queryOne = customerAndCategoryMapper.queryOne("5b1309d52a80457a84bc5257d9af9166", "6ee2469963db49e8bf127e1b22148db6");
	System.out.println(queryOne);
    }
    @Test
    public void test10() throws IOException{
	
	List<CustomerLogoFix> logoFixsByCustomerId = customerLogoFixService.getLogoFixsByCustomerId("23226617a47c400bbe9d90658c425fd8", 0L, 1L);
    
	//String objectToJson = JsonUtil.objectToJson(logoFixsByCustomerId);
	
	System.out.println(logoFixsByCustomerId);
    }
    @Test
    public void test7(){
	CustomerLogoFixExample customerLogoFixExample = new CustomerLogoFixExample();
	
	List<CustomerLogoFix> selectByExampleAndItemAndCustomer = 
		customerLogoFixMapper.selectByExampleAndItemAndCustomer("23226617a47c400bbe9d90658c425fd8", 0L,0L,15L);
    
	for (CustomerLogoFix customerLogoFix : selectByExampleAndItemAndCustomer) {
	    Long created = customerLogoFix.getCreated();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String format = formatter.format(created);
	    System.out.println(format);
	}
    }
    @Test
    public void test5(){
	GiftItemCategoryExample giftItemCategoryExample = new GiftItemCategoryExample();
	
	com.gift.vo.GiftItemCategoryExample.Criteria createCriteria = giftItemCategoryExample.createCriteria();
	
	createCriteria.andParentidNotEqualTo(0l);
	List<GiftItemCategory> selectByExample = giftItemCategoryMapper.selectByExample(giftItemCategoryExample);
	
	List<TreeJson> list = new ArrayList<TreeJson>();
	for (GiftItemCategory giftItemCategory : selectByExample) {
	    TreeJson treeJson = new TreeJson();
	    treeJson.setId(giftItemCategory.getId());
	    treeJson.setName(giftItemCategory.getName());
	    treeJson.setpId(giftItemCategory.getParentid());
	    list.add(treeJson);
	}
	System.out.println(list);
    }
    @Test
    public void test13(){
/*	Jedis resource = jedisPool.getResource();
	System.out.println(resource);
	long del = jedisClientSingle.del("23226617a47c400bbe9d90658c425fd8");
	String hget = jedisClientSingle.hget("23226617a47c400bbe9d90658c425fd8"+"logofix", "3");
	System.out.println(hget);
	System.out.println(del);
	//customerLogoFixService.addCustomerLogoFix("aaa", null, "aaa");
*/    
/*	ApplyUserExample applyUserExample = new ApplyUserExample();
	
	com.gift.vo.ApplyUserExample.Criteria createCriteria = applyUserExample.createCriteria();
	
	createCriteria.andNameLike("%中%");
	
	
	for (ApplyUser applyUser : selectByExample) {
	    System.out.println(applyUser.getName());
	}*/
	List<Customer> selectByNameOrTelOrEamil = customerMapper.selectByNameOrTelOrEamil("%宁%", 0L);
	
	for (Customer customer : selectByNameOrTelOrEamil) {
	    System.out.println(customer.getName());
	}
	}
    @Test
    public void test9(){
	Long title = 31l;
	Long num  = 15l;
	//0 15 30 45 
	//1 2 3 4
	Long page1 = 1l;
/*	Long aaa = (page1-1)*15;
	Long i = title/num;//页数
	System.out.println(i);*/
	Page page = new Page();
	
	System.out.println(page.getbeginNum(page1));
    }
}
