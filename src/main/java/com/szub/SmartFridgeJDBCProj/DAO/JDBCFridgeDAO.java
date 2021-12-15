package com.szub.SmartFridgeJDBCProj.DAO;

import com.szub.SmartFridgeJDBCProj.Tables.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("contactDao")
public class JDBCFridgeDAO implements FridgeDAO{
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SelectAllRecipes selectAllRecipes;
    private SelectAllProducts selectAllProducts;
    private SelectAllFromFridge selectAllFromFridge;
    private InsertProducts insertProducts;
    private DeleteProductsFromRecipe deleteProducts;
    private DeleteProductFromFridge deleteProductFromFridge;
    private InsertProductInFridge insertProductInFridge;


    @Override
    public List<RecipeTable> allRecipes() {

        return selectAllRecipes.execute();
    }
    @Override
    public List<ProductTable> allProducts() {
        return selectAllProducts.execute();
    }

    @Override
    public List<FridgeTable> fridge() {
//        String sql = "SELECT f.id, f.quantity, f.id_of_product, pr.id as products, pr.name FROM fridge f left join products pr on f.id_of_product = pr.id";
//        return namedParameterJdbcTemplate.query(sql, new FridgeDetailExtractor());
        return selectAllFromFridge.execute();
    }

    @Override
    public List<ProductInRecipe> findProductNotInRecipe(Long recipId) {
        String sql = "SELECT * FROM products WHERE NOT EXISTS (SELECT * FROM product_to_recipe WHERE products.id = product_to_recipe.product_id AND product_to_recipe.recipes_id = :IdRecip)";
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("IdRecip", recipId);
        return (List<ProductInRecipe>) namedParameterJdbcTemplate.query(sql, namedParameters, new ProductsNotInRec());
    }

    @Override
    public void insertIntoRecipe(ProductInRecipe productInRecipe) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("product_id", productInRecipe.getId());
        paramMap.put("quantity", productInRecipe.getQuantity());
        paramMap.put("recipes_id", productInRecipe.getRecipeId());
        insertProducts.updateByNamedParam(paramMap);
    }

    @Override
    public void deleteProductFromRecipe(ProductInRecipe productInRecipe) {
       Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pId", productInRecipe.getId());
        //paramMap.put("quantity", productInRecipe.getQuantity());
        paramMap.put("rId", productInRecipe.getRecipeId());
        deleteProducts.updateByNamedParam(paramMap);
    }

    @Override
    public void deleteProductFromFridge(FridgeTable fridgeTable) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pId", fridgeTable.getId());
        deleteProductFromFridge.updateByNamedParam(paramMap);
    }

    @Override
    public void insertProductInFridge(FridgeTable fridgeTable) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id_of_product", fridgeTable.getProductID());
        paramMap.put("quantity", fridgeTable.getQuantity());
        insertProductInFridge.updateByNamedParam(paramMap);
    }

    @Override
    public List<ProductTable> findProductNotInFridge() {
        String sql = "SELECT * FROM products WHERE NOT EXISTS(SELECT * FROM fridge WHERE products.id = fridge.id_of_product)";
        //Map<String, Object> paramMap = new HashMap<String, Object>();

        return (List<ProductTable>) namedParameterJdbcTemplate.query(sql, new ProductNotInFridge());
    }

    @Override
    public List<Order> findProductForOrder(Long recipeId) {
        String sql = "select pr.id, pr.name, pr.measure, p2r.quantity, pr.cost, p2r.quantity*pr.cost as price \n" +
                "from products pr left join product_to_recipe p2r on pr.id = p2r.product_id \n" +
                "where recipes_id = :recId \n" +
                "and not exists (select * from fridge f where f.id_of_product = pr.id)\n" +
                "union\n" +
                "select pr.id, pr.name, pr.measure, p2r.quantity-f.quantity quantity, pr.cost, (p2r.quantity-f.quantity)*pr.cost as price \n" +
                "from products pr \n" +
                "left join product_to_recipe p2r on pr.id = p2r.product_id left join fridge f on f.id_of_product = pr. id\n" +
                "where recipes_id = :recId and p2r.quantity > f.quantity\n" +
                "order by 2";
        Map<String, Object> namedParam = new HashMap<String, Object>();
        namedParam.put("recId", recipeId);
        return (List<Order>) namedParameterJdbcTemplate.query(sql,namedParam, new OrderProducts());
    }


    @Override
    public List<ProductInRecipe> findProductsForRecipe(Long recipId) {
        String sql = "SELECT ptr.quantity, ptr.product_id, ptr.recipes_id, pr.id as products, pr.name FROM product_to_recipe ptr left join products pr on ptr.product_id = pr.id WHERE  ptr.recipes_id = :IdRecip";
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("IdRecip", recipId);
        return (List<ProductInRecipe>) namedParameterJdbcTemplate.query(sql,namedParameters, new ProductMapper());
        //return namedParameterJdbcTemplate.
    }

    @Override
    public RecipeTable findRecipeById(Long id) {
        String sql = "SELECT * FROM recipes WHERE  id = :recipID";
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("recipID", id);
        return namedParameterJdbcTemplate.queryForObject(sql,namedParameters, new RecipeRowMapper());
    }

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.selectAllRecipes = new SelectAllRecipes(dataSource);
        this.selectAllProducts = new SelectAllProducts(dataSource);
        this.selectAllFromFridge = new SelectAllFromFridge(dataSource);
        this.insertProducts = new InsertProducts(dataSource);
        this.deleteProducts = new DeleteProductsFromRecipe(dataSource);
        this.deleteProductFromFridge = new DeleteProductFromFridge(dataSource);
        this.insertProductInFridge = new InsertProductInFridge(dataSource);
    }


    private static final class DetailExtractor implements ResultSetExtractor<List<RecipeTable>> {
    @Override
    public List<RecipeTable> extractData(ResultSet rt) throws SQLException,
            DataAccessException {
        Map<Long, RecipeTable> map = new HashMap<Long, RecipeTable>();
        RecipeTable recipeTable = null;
        while (rt.next()) {
            Long id = rt. getLong ( "recipes_id");
            System.out.println("rt id"+id);
            recipeTable = map.get(id) ;
            if (recipeTable == null) {
                System.out.println("Creating recipe table");
                recipeTable = new RecipeTable();
                recipeTable.setId(id);
                recipeTable.setProductTables(new ArrayList<ProductTable>());
                map.put(id, recipeTable);
            }
            Long product = rt. getLong ( "product_id");
            System.out.println("product" + product);
            if (product>0){
                System.out.println("Creating pt");
                ProductInRecipe productTable = new ProductInRecipe();
                productTable.setName(rt.getString("name"));
                productTable.setQuantity(rt.getLong("quantity"));
                recipeTable.getProductTables().add(productTable);
            }
        }
        return new ArrayList<RecipeTable> (map.values());
    }
}

    static class ProductMapper implements RowMapper{

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            System.out.print("Read product =>> ");
            ProductInRecipe productTable = new ProductInRecipe();
            productTable.setId(resultSet.getLong("product_id"));
            productTable.setRecipeId(resultSet.getLong("recipes_id"));
            productTable.setName(resultSet.getString("name"));
            productTable.setQuantity(resultSet.getLong("quantity"));
            System.out.println(productTable);
            return productTable;
        }
    }
    static class ProductsNotInRec implements RowMapper{

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
          ProductTable productTable = new ProductTable();
          productTable.setId(resultSet.getLong("id"));
          productTable.setName(resultSet.getString("name"));
            System.out.println(productTable);
            return productTable;
        }
    }
    static class ProductNotInFridge implements RowMapper{

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            ProductTable productTable = new ProductTable();
            productTable.setId(resultSet.getLong("id"));
            productTable.setName(resultSet.getString("name"));
            System.out.println("ProductNotInFridge -> "+productTable);
            //fridgeTable.setQuantity(resultSet.getLong("quantity"));
            //fridgeTable.setId(resultSet.getLong("id"));
            return productTable;
        }
    }
    static class OrderProducts implements RowMapper{

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Order productTable = new Order();
            productTable.setId(resultSet.getLong("id"));
            productTable.setName(resultSet.getString("name"));
            productTable.setCost(resultSet.getLong("cost"));
            productTable.setPrice(resultSet.getLong("price"));
            productTable.setQuantity(resultSet.getLong("quantity"));
            return productTable;
        }
    }


}

//    select pr.id, pr.name, pr.measure, p2r.quantity, pr.cost, p2r.quantity*pr.cost as price
//        from products pr left join product_to_recipe p2r on pr.id = p2r.product_id
//        where recipes_id = 1
//        and not exists (select * from fridge f where f.id_of_product = pr.id)
//        union
//        select pr.id, pr.name, pr.measure, p2r.quantity-f.quantity quantity, pr.cost, (p2r.quantity-f.quantity)*pr.cost as price
//        from products pr
//        left join product_to_recipe p2r on pr.id = p2r.product_id
//        left join fridge f on f.id_of_product = pr. id
//        where recipes_id = 1 and p2r.quantity > f.quantity
//        order by 2