package com.javaweb.utils;

import com.javaweb.model.dto.AbstractDTO;
import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import javax.servlet.http.HttpServletRequest;

public class DisplayTagUtils {

    public static void of(HttpServletRequest request, AbstractDTO dto) {
        if (dto != null) {
            String sPage = request.getParameter(new ParamEncoder(dto.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE));
            Integer page = 1;
            if (StringUtils.isNotBlank(sPage)) {
                try {
                    page = Integer.valueOf(sPage);
                } catch (Exception e) {
                }
            }
            dto.setPage(page);
        }
    }
}
