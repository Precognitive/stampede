/*
 *     This file is part of ToroDB.
 *
 *     ToroDB is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     ToroDB is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with ToroDB. If not, see <http://www.gnu.org/licenses/>.
 *
 *     Copyright (c) 2014, 8Kdata Technology
 *     
 */

package com.torodb.packaging.config.model.generic;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.torodb.core.metrics.MetricsConfig;
import com.torodb.packaging.config.annotation.Description;
import com.torodb.packaging.config.model.generic.log4j.Log4jLevelToLogLevelMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Description("config.generic")
@JsonPropertyOrder({ 
	"logLevel", 
	"logFile", 
	"connectionPoolSize", 
	"reservedReadPoolSize" 
})
public class Generic implements MetricsConfig{

	@Description("config.generic.logLevel")
	@NotNull
	@JsonProperty(required=true)
	private LogLevel logLevel;
	@Description("config.generic.logPackages")
	private LogPackages logPackages;
	@Description("config.generic.logFile")
	private String logFile;
	@Description("config.generic.log4j2File")
	private String log4j2File;
    @Description("config.generic.connectionPoolTimeout")
    @NotNull
    @JsonProperty(required=true)
    private Long connectionPoolTimeout = 10L * 1000;
    @Description("config.generic.connectionPoolSize")
    @NotNull
    @Min(3)
    @JsonProperty(required=true)
    private Integer connectionPoolSize = 30;
	@Description("config.generic.reservedReadPoolSize")
	@NotNull
	@Min(1)
	@JsonProperty(required=true)
	private Integer reservedReadPoolSize = 10;
	@Description("config.generic.metricsEnabled")
	private Boolean metricsEnabled = true;

	public Generic() {
		Level log4jLevel = LogManager.getRootLogger().getLevel();
		logLevel = new Log4jLevelToLogLevelMapper().map(log4jLevel);
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public LogPackages getLogPackages() {
		return logPackages;
	}

	public void setLogPackages(LogPackages logPackages) {
		this.logPackages = logPackages;
	}

	public String getLogFile() {
		return logFile;
	}

	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	public String getLog4j2File() {
		return log4j2File;
	}

	public void setLog4j2File(String log4j2File) {
		this.log4j2File = log4j2File;
	}

	public Long getConnectionPoolTimeout() {
        return connectionPoolTimeout;
    }

    public void setConnectionPoolTimeout(Long connectionPoolTimeout) {
        this.connectionPoolTimeout = connectionPoolTimeout;
    }

    public Integer getConnectionPoolSize() {
		return connectionPoolSize;
	}

	public void setConnectionPoolSize(Integer connectionPoolSize) {
		this.connectionPoolSize = connectionPoolSize;
	}

	public Integer getReservedReadPoolSize() {
		return reservedReadPoolSize;
	}

	public void setReservedReadPoolSize(Integer reserverdReadPoolSize) {
		this.reservedReadPoolSize = reserverdReadPoolSize;
	}

	@Override
	public Boolean getMetricsEnabled() {
		return metricsEnabled;
	}
	
	public void setMetricsEnabled(Boolean metricsEnabled){
		if (metricsEnabled!=null){
			this.metricsEnabled = metricsEnabled;
		}
	}

}
