package com.test.contact_jenius.Features.EditActivity;

import com.test.contact_jenius.Models.delete;
import com.test.contact_jenius.Models.wraperContectById;

public interface EditView {
    void ContactCategoriSuccess(wraperContectById model);

    void DeleteSuccess(delete model);
}
