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

package in.zapr.druid.druidry.extensions.distinctcount.aggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DistinctCountAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        DistinctCountAggregator distinctCountAggregator =
                new DistinctCountAggregator("uniqueStars", "allStars");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "distinctCount");
        jsonObject.put("name", "uniqueStars");
        jsonObject.put("fieldName", "allStars");

        String actualJSON = objectMapper.writeValueAsString(distinctCountAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFields() {

        DistinctCountAggregator distinctCountAggregator =
                new DistinctCountAggregator(null, null);
    }

    @Test
    public void testEqualsPositive() {

        DistinctCountAggregator aggregator1 =
                new DistinctCountAggregator("uniqueStars", "allStars");
        DistinctCountAggregator aggregator2 =
                new DistinctCountAggregator("uniqueStars", "allStars");

        Assert.assertEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsNegative() {

        DistinctCountAggregator aggregator1 =
                new DistinctCountAggregator("uniqueStars1", "allStars1");
        DistinctCountAggregator aggregator2 =
                new DistinctCountAggregator("uniqueStars2", "allStars2");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }

}
