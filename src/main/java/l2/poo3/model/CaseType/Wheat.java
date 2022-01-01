package l2.poo3.model.CaseType;

import l2.poo3.model.CaseModel;
import l2.poo3.model.Enum.Case;
import l2.poo3.model.Enum.Resources;

public class Wheat extends CaseModel {

    private final Resources produce = Resources.BLE;

    public Wheat() {
        super(Case.CHAMPS, Resources.BLE);
    }
}
