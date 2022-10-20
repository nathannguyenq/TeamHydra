package org.example.Controller;

import org.example.Model.Items;
import org.example.View.View;

public class Controller {

    private Items items_;

    View view = new View();

    public Controller(Items items_) {
        this.items_ = items_;
    }

    public String getItemName() {
        return items_.getiName();
    }
}
