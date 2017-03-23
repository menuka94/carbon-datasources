/*
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wso2.carbon.datasource.core.beans;

import org.wso2.carbon.datasource.utils.DataSourceUtils;
import org.wso2.carbon.kernel.annotations.Configuration;
import org.wso2.carbon.kernel.annotations.Element;
import org.yaml.snakeyaml.Yaml;


/**
 * Class holding the data source definition.
 */
@Configuration(description = "data source definition")
public class DataSourceDefinition {

    @Element(description = "data source type", required = true)
    private String type;

    @Element(description = "data source configuration")
    private Object configuration;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Object configuration) {
        this.configuration = configuration;
    }

    public boolean equals(Object rhs) {
        if (!(rhs instanceof DataSourceDefinition)) {
            return false;
        }
        DataSourceDefinition dsDef = (DataSourceDefinition) rhs;
        if (!DataSourceUtils.nullAllowEquals(dsDef.getType(), this.getType())) {
            return false;
        }
        Yaml yaml = new Yaml();
        return DataSourceUtils.nullAllowEquals(yaml.dumpAsMap(
                dsDef.configuration),
                yaml.dumpAsMap(this.getConfiguration()));
    }

    @Override
    public int hashCode() {
        if (type != null && configuration != null) {
            Yaml yaml = new Yaml();
            return type.hashCode() + yaml.dumpAsMap(this.getConfiguration()).hashCode();
        } else {
            return super.hashCode();
        }
    }
}
