import javax.swing.JOptionPane;

import javax.swing.JTextField;

public class ProductRemovalDialogBox {

    private final JTextField categoryField = new JTextField();

    private final JTextField productField = new JTextField();

    private final JTextField quantityField = new JTextField();



    private final Object[] fields = {
            "Category", categoryField,
            "Product", productField
    };

    public ProductRemovalDialogBox() {
        JOptionPane.showConfirmDialog(null, fields, "Select Category and product to remove", JOptionPane.OK_CANCEL_OPTION);
    }

    public String getCategoryFieldText() {
        return categoryField.getText();
    }

    public String getProductFieldText() {
        return productField.getText();
    }

}