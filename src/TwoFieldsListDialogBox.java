import javax.swing.*;

public class TwoFieldsListDialogBox {

    private JComboBox<String> cBox = new JComboBox<>(Product.allowedTypes);

    private JTextField quantityField = new JTextField();

    private JTextField productField = new JTextField();

    private JTextField categoryField = new JTextField();

    private final Object[] fields = {
            "type", cBox,
            "quantity", quantityField,
            "product", productField,
            "category", categoryField
    };

    public TwoFieldsListDialogBox() {
        JOptionPane.showConfirmDialog(null, fields, "Select product to add", JOptionPane.OK_CANCEL_OPTION);
    }

    public String getTypeLabelContents() {
        return (String)(cBox.getSelectedItem());
    }

    public String getQuantityLabelContents() {
        return quantityField.getText();
    }

    public String getProductLabelContents() {
        return productField.getText();
    }

    public String getCategoryLabelContents() {
        return categoryField.getText();
    }
}
