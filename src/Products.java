public class Products {
    protected String[] nameProduct;
    protected int[] priceProduct;

    public Products(String[] name, int[] price){
        this.nameProduct = name;
        this.priceProduct = price;
    }

    public void printList(){
        int i = 0;
        while (i < nameProduct.length){
            System.out.println(i + 1 + ". " + nameProduct[i] + " " + priceProduct[i] + " руб/шт" );
            i++;
        }
    }

}
