import javax.swing.JOptionPane;

import javax.swing.JTextField;

public class TwoFieldsDialogBox {

    private final JTextField categoryField = new JTextField();

    private final JTextField productField = new JTextField();

    private final Object[] fields = {
            "Category", categoryField,
            "Product", productField
    };

    public TwoFieldsDialogBox() {
        JOptionPane.showConfirmDialog(null, fields, "Select Category and product to remove", JOptionPane.OK_CANCEL_OPTION);
    }

    public String getCategoryFieldText() {
        return categoryField.getText();
    }

    public String getProductFieldText() {
        return productField.getText();
    }

}