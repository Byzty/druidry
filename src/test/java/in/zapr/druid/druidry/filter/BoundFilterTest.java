/*
 * Copyright (c) 2017-present, Red Brick Lane Marketing Solutions Pvt. Ltd.
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package in.zapr.druid.druidry.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import in.zapr.druid.druidry.SortingOrder;

public class BoundFilterTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {

        BoundFilter filter = BoundFilter.builder()
                .dimension("Hello")
                .lower("21")
                .upper("31")
                .lowerStrict(true)
                .upperStrict(false)
                .ordering(SortingOrder.ALPHANUMERIC)
                .build();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "bound");
        jsonObject.put("dimension", "Hello");
        jsonObject.put("lower", "21");
        jsonObject.put("upper", "31");
        jsonObject.put("lowerStrict", true);
        jsonObject.put("upperStrict", false);
        jsonObject.put("ordering", "alphanumeric");

        String actualJSON = objectMapper.writeValueAsString(filter);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testRequiredFieldsMissing() {
        BoundFilter filter = BoundFilter.builder()
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testSettingRequiredFieldAsNull() {
        BoundFilter filter = BoundFilter.builder()
                .dimension("Hello")
                .lower("21")
                .upper("31")
                .lowerStrict(true)
                .upperStrict(false)
                .ordering(SortingOrder.ALPHANUMERIC)
                .build();

        filter.setDimension(null);
    }
}
