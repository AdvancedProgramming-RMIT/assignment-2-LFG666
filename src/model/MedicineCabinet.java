package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MedicineCabinet {
	private StringProperty name = new SimpleStringProperty();
	private IntegerProperty quantity = new SimpleIntegerProperty();
	private IntegerProperty stocklev = new SimpleIntegerProperty();

	public MedicineCabinet() {
		name.set("");
		quantity.set(0);
		stocklev.set(0);
	}
	
	@Override
	public String toString() {
		return  name.get() + "," + quantity.get() + "," + stocklev.get();
	}

	public MedicineCabinet(String name, int quantity, int stocklev) {
		this.name.set(name);
		this.quantity.set(quantity);
		this.stocklev.set(stocklev);

	}

	public final String getName() { return name.get(); }
	public final int getQuantity() { return quantity.get(); }
	public final int getStocklev() { return stocklev.get(); }

	public final void setName(String name) { this.name.set(name); }
	public final void setQuantity(int unitQuantity) { this.quantity.set(unitQuantity); }
	public final void setStocklev(int unitStocklev) { this.stocklev.set(unitStocklev); }

	public StringProperty nameProperty() { return name; }
	public IntegerProperty quantityProperty() { return quantity; }
	public IntegerProperty stocklevProperty() { return stocklev; }
}
