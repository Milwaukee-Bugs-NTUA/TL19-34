package gr.ntua.ece.softeng19b.data;

import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.model.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import java.util.List;

public class DataAccess {

    private static final Object[] EMPTY_ARGS = new Object[0];

    private static final int MAX_TOTAL_CONNECTIONS = 16;
    private static final int MAX_IDLE_CONNECTIONS = 8;

    private JdbcTemplate jdbcTemplate;

    public void setup(String driverClass, String url, String user, String pass) throws SQLException {

        //initialize the data source
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(driverClass);
        bds.setUrl(url);
        bds.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        bds.setMaxIdle(MAX_IDLE_CONNECTIONS);
        bds.setUsername(user);
        bds.setPassword(pass);
        bds.setValidationQuery("SELECT 1");
        bds.setTestOnBorrow(true);
        bds.setDefaultAutoCommit(true);

        //check that everything works OK
        bds.getConnection().close();

        //initialize the jdbc template utility
        jdbcTemplate = new JdbcTemplate(bds);
    }

    public void accessDataCheck() throws DataAccessException {
        try {
            jdbcTemplate.query("select 1", ResultSet::next);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }


    public List<ATLRecordForSpecificDay> fetchActualDataLoadForSpecificDate(String areaName, String resolution, LocalDate date) throws DataAccessException {

        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month,
                day
        };

        //TODO: Insert a valid SQL query
        String sqlQuery = "select atl.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, atl.year, atl.month, atl.day, atl.TotalLoadValue "+
                          "from actualtotalload as atl, resolutioncode as rc, areatypecode as atc, mapcode as mc " +
                          "where atl.areaname=? and rc.resolutioncodetext=? and atl.Year=? and atl.Month=? and atl.Day=? " +
                          "and rc.Id=atl.ResolutionCodeId and mc.id=atl.mapcodeid and atc.id=atl.AreaTypeCodeId";
		try {
					return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						ATLRecordForSpecificDay dataLoad = new ATLRecordForSpecificDay();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDay(rs.getInt(7));
						dataLoad.setActualTotalLoadValue(rs.getDouble(8));
						return dataLoad;

					});
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<DATLFRecordForSpecificDay> fetchDayAheadTotalLoadForecastForSpecificDate(String areaName, String resolution, LocalDate date) throws DataAccessException {

        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month,
                day
        };

        //TODO: Insert a valid SQL query
        String sqlQuery = "select datlf.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, datlf.year, datlf.month, datlf.day, datlf.TotalLoadValue "+
                          "from dayaheadtotalloadforecast as datlf, resolutioncode as rc, areatypecode as atc, mapcode as mc " +
                          "where datlf.areaname=? and rc.resolutioncodetext=? and datlf.Year=? and datlf.Month=? and datlf.Day=? " +
                          "and rc.Id=datlf.ResolutionCodeId and mc.id=datlf.mapcodeid and atc.id=datlf.AreaTypeCodeId";
		try {
					return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						DATLFRecordForSpecificDay dataLoad = new DATLFRecordForSpecificDay();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDay(rs.getInt(7));
						dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(8));
						return dataLoad;

					});
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<DATLFRecordForSpecificMonth> fetchDayAheadTotalLoadForecastForSpecificMonth(String areaName, String resolution, YearMonth yearMonth) throws DataAccessException {

        Integer year = yearMonth.getYear();
        Integer month = yearMonth.getMonthValue();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month
        };

        //TODO: Insert a valid SQL query
        String sqlQuery = "select datlf.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, datlf.year, "+
                          "datlf.month, datlf.day, sum(datlf.TotalLoadValue) as TotalLoadForecastValue "+
                          "from dayaheadtotalloadforecast as datlf, resolutioncode as rc, areatypecode as atc, mapcode as mc " +
                          "where datlf.areaname=? and rc.resolutioncodetext=? and datlf.Year=? and datlf.Month=? " +
                          "and rc.Id=datlf.ResolutionCodeId and mc.id=datlf.mapcodeid and atc.id=datlf.AreaTypeCodeId " +
                          "group by datlf.day, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext order by datlf.day asc";
					
		try {
                        return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						DATLFRecordForSpecificMonth dataLoad = new DATLFRecordForSpecificMonth();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDay(rs.getInt(7));
                        dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(8));
						return dataLoad;
					});

        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }

    }
    public List<DATLFRecordForSpecificYear> fetchDayAheadTotalLoadForecastForSpecificYear(String areaName, String resolution, Year year) throws DataAccessException {

        Integer yearInt = year.getValue();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                yearInt
            };

        //TODO: Insert a valid SQL query
        String sqlQuery = "select datlf.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, datlf.year, "+
                          "datlf.month, sum(datlf.TotalLoadValue) as TotalLoadForecastValue "+
                          "from dayaheadtotalloadforecast as datlf, resolutioncode as rc, areatypecode as atc, mapcode as mc " +
                          "where datlf.areaname=? and rc.resolutioncodetext=? and datlf.Year=? " +
                          "and rc.Id=datlf.ResolutionCodeId and mc.id=datlf.mapcodeid and atc.id=datlf.AreaTypeCodeId " +
                          "group by datlf.month, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext order by datlf.month asc";
		try {
					return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						DATLFRecordForSpecificYear dataLoad = new DATLFRecordForSpecificYear();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(7));
						return dataLoad;
					});

        }        
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    public List<AGPTRecordForSpecificDay> fetchAggregatedGenerationPerTypeForSpecificDate(String areaName, String resolution, String productionType, LocalDate date) throws DataAccessException {

        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();

        String sqlQuery;
        Object[] sqlParams;
        //TODO: Insert a valid SQL query
        if(!productionType.equals("AllTypes")){
            
            sqlParams = new Object[] {
                areaName,
                resolution,
                productionType,
                year,
                month,
                day
            };
            
            sqlQuery = "select agpt.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, agpt.year, agpt.month, "+
                       "agpt.day, pt.productiontypetext, agpt.actualgenerationoutput "+
                       "from aggregatedgenerationpertype as agpt, resolutioncode as rc, areatypecode as atc, mapcode as mc, productiontype as pt " +
                       "where agpt.areaname=? and rc.resolutioncodetext=? and pt.productiontypetext=? and agpt.Year=? and agpt.Month=? and agpt.Day=? " +
                       "and rc.Id=agpt.ResolutionCodeId and mc.id=agpt.mapcodeid and atc.id=agpt.AreaTypeCodeId and pt.id=agpt.productiontypeid";
        }
        else{
            
            sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month,
                day
            };
            
            sqlQuery = "select agpt.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, agpt.year, agpt.month, "+
                       "agpt.day, pt.productiontypetext, agpt.actualgenerationoutput "+
                       "from aggregatedgenerationpertype as agpt, resolutioncode as rc, areatypecode as atc, mapcode as mc, productiontype as pt " +
                       "where agpt.areaname=? and rc.resolutioncodetext=? and agpt.Year=? and agpt.Month=? and agpt.Day=? " +
                       "and rc.Id=agpt.ResolutionCodeId and mc.id=agpt.mapcodeid and atc.id=agpt.AreaTypeCodeId and pt.id=agpt.productiontypeid";
        } 
		try {
					return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						AGPTRecordForSpecificDay dataLoad = new AGPTRecordForSpecificDay();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
                        dataLoad.setDay(rs.getInt(7));
                        dataLoad.setProductionType(rs.getString(8));
						dataLoad.setActualGenerationOutputValue(rs.getDouble(9));
						return dataLoad;

					});
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<AGPTRecordForSpecificMonth> fetchAggregatedGenerationPerTypeForSpecificMonth(String areaName, String resolution, String productionType, YearMonth yearMonth) throws DataAccessException {

        Integer year = yearMonth.getYear();
        Integer month = yearMonth.getMonthValue();

        Object[] sqlParams;

        //TODO: Insert a valid SQL query
        String sqlQuery;

        if(!productionType.equals("AllTypes")){
            
            sqlParams = new Object[] {
                areaName,
                resolution,
                productionType,
                year,
                month
            };

            sqlQuery = "select agpt.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, agpt.year, agpt.month, "+
                       "agpt.day, pt.productiontypetext, sum(agpt.actualgenerationoutput) "+
                       "from aggregatedgenerationpertype as agpt, resolutioncode as rc, areatypecode as atc, mapcode as mc, productiontype as pt " +
                       "where agpt.areaname=? and rc.resolutioncodetext=? and pt.productiontypetext=? and agpt.Year=? and agpt.Month=? " +
                       "and rc.Id=agpt.ResolutionCodeId and mc.id=agpt.mapcodeid and atc.id=agpt.AreaTypeCodeId and pt.id=agpt.productiontypeid "+
                       "group by agpt.day, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, pt.productiontypetext order by agpt.day";
        }
        else{
        
            sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month
            };
        
            sqlQuery = "select agpt.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, agpt.year, agpt.month, "+
                       "agpt.day, pt.productiontypetext, sum(agpt.actualgenerationoutput) "+
                       "from aggregatedgenerationpertype as agpt, resolutioncode as rc, areatypecode as atc, mapcode as mc, productiontype as pt " +
                       "where agpt.areaname=? and rc.resolutioncodetext=? and agpt.Year=? and agpt.Month=? " +
                       "and rc.Id=agpt.ResolutionCodeId and mc.id=agpt.mapcodeid and atc.id=agpt.AreaTypeCodeId and pt.id=agpt.productiontypeid "+
                       "group by agpt.day, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, pt.productiontypetext order by agpt.day"; 
        }
					
		try {
                        return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						AGPTRecordForSpecificMonth dataLoad = new AGPTRecordForSpecificMonth();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDay(rs.getInt(7));
                        dataLoad.setProductionType(rs.getString(8));
                        dataLoad.setActualGenerationOutputValue(rs.getDouble(9));
                        return dataLoad;
					});

        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }

    }
    public List<AGPTRecordForSpecificYear> fetchAggregatedGenerationPerTypeForSpecificYear(String areaName, String resolution, String productionType, Year year) throws DataAccessException {

        Integer yearInt = year.getValue();

        Object[] sqlParams;
        

        //TODO: Insert a valid SQL query
        String sqlQuery;

        if(!productionType.equals("AllTypes")){

            sqlParams = new Object[] {
                areaName,
                resolution,
                productionType,
                yearInt
            };

            sqlQuery = "select agpt.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, agpt.year, agpt.month, "+
                       "pt.productiontypetext, sum(agpt.actualgenerationoutput) "+
                       "from aggregatedgenerationpertype as agpt, resolutioncode as rc, areatypecode as atc, mapcode as mc, productiontype as pt " +
                       "where agpt.areaname=? and rc.resolutioncodetext=? and pt.productiontypetext=? and agpt.Year=? " +
                       "and rc.Id=agpt.ResolutionCodeId and mc.id=agpt.mapcodeid and atc.id=agpt.AreaTypeCodeId and pt.id=agpt.productiontypeid "+
                       "group by agpt.month, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, pt.productiontypetext order by agpt.month";
        }
        else{

            sqlParams = new Object[] {
                areaName,
                resolution,
                yearInt
            };

            sqlQuery = "select agpt.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, agpt.year, agpt.month, "+
                       "pt.productiontypetext, sum(agpt.actualgenerationoutput) "+
                       "from aggregatedgenerationpertype as agpt, resolutioncode as rc, areatypecode as atc, mapcode as mc, productiontype as pt " +
                       "where agpt.areaname=? and rc.resolutioncodetext=? and agpt.Year=? " +
                       "and rc.Id=agpt.ResolutionCodeId and mc.id=agpt.mapcodeid and atc.id=agpt.AreaTypeCodeId and pt.id=agpt.productiontypeid "+
                       "group by agpt.month, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, pt.productiontypetext order by agpt.month"; 
        }

		try {
					return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						AGPTRecordForSpecificYear dataLoad = new AGPTRecordForSpecificYear();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setMonth(rs.getInt(6));
                        dataLoad.setProductionType(rs.getString(7));
                        dataLoad.setActualGenerationOutputValue(rs.getDouble(8));
						return dataLoad;
					});

        }        
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<ATLRecordForSpecificMonth> fetchActualDataLoadForSpecificMonth(String areaName, String resolution, YearMonth yearmonth) throws DataAccessException {
            
        Integer year = yearmonth.getYear();

        Integer month = yearmonth.getMonthValue();
        Object[] sqlParams = new Object[] {
            areaName,
            resolution,
            year,
            month
        };

        String sqlQuery = "select atl.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, atl.year, atl.month, atl.day, sum(atl.TotalLoadValue) "+
                        "from actualtotalload as atl, resolutioncode as rc, areatypecode as atc, mapcode as mc "+
                        "where atl.areaname=? and rc.resolutioncodetext=? and atl.Year=? and atl.Month=? "+
                        "and rc.Id=atl.ResolutionCodeId and mc.id=atl.mapcodeid and atc.id=atl.AreaTypeCodeId "+
                        "group by atl.day, atc.AreaTypeCodeText, mc.MapCodeText, rc.ResolutionCodeText order by atl.day";

        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
            ATLRecordForSpecificMonth dataLoad = new ATLRecordForSpecificMonth();
            dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
            dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
            dataLoad.setMapCode(rs.getString(3));
            dataLoad.setYear(rs.getInt(5));
            dataLoad.setResolutionCode(rs.getString(4));
            dataLoad.setMonth(rs.getInt(6));
            dataLoad.setDay(rs.getInt(7));
            dataLoad.setActualTotalLoadByDayValue(rs.getDouble(8));
        
            return dataLoad;
            });
        }
        catch(Exception e) {
                    throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<ATLRecordForSpecificYear> fetchActualDataLoadForSpecificYear(String areaName, String resolution, Year year) throws DataAccessException {
        Integer year1 = year.getValue();
        
        Object[] sqlParams = new Object[] {

        areaName,
        resolution,
        year1
        };

        String sqlQuery = "select atl.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, atl.year, atl.month, sum(atl.TotalLoadValue) "+
                    "from actualtotalload as atl, resolutioncode as rc, areatypecode as atc, mapcode as mc "+
                    "where atl.areaname=? and rc.resolutioncodetext=? and atl.Year=? "+
                    "and rc.Id=atl.ResolutionCodeId and mc.id=atl.mapcodeid and atc.id=atl.AreaTypeCodeId "+
                    "group by atl.Month, atc.AreaTypeCodeText, mc.MapCodeText, rc.ResolutionCodeText order by atl.Month";

        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
            ATLRecordForSpecificYear dataLoad = new ATLRecordForSpecificYear();
            dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
            dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
            dataLoad.setMapCode(rs.getString(3));
            dataLoad.setResolutionCode(rs.getString(4));
            dataLoad.setYear(rs.getInt(5));
            dataLoad.setMonth(rs.getInt(6));
            //dataLoad.setDay(rs.getInt(7));
            dataLoad.setActualDataLoadByMonthValue(rs.getDouble(7));
            return dataLoad;

            });
        }
        catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
        }


    }

    public List<AVFRecordForSpecificDay> fetchActualvsForecastForSpecificDate(String areaName, String resolution, LocalDate date) throws DataAccessException {

        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month,
                day
        };

        //TODO: Insert a valid SQL query
        String sqlQuery = "select datlf.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, datlf.year, datlf.month, "+
        "datlf.day, datlf.TotalLoadValue, atl.TotalLoadValue, atl.DateTime "+
        "from dayaheadtotalloadforecast as datlf, actualtotalload as atl, resolutioncode as rc, areatypecode as atc, mapcode as mc "+
        "where datlf.areaname=? and rc.resolutioncodetext=? and datlf.Year=? and datlf.Month=? and datlf.Day=? "+
        "and rc.Id=datlf.ResolutionCodeId and mc.id=datlf.mapcodeid and atc.id=datlf.AreaTypeCodeId and "+
        "atl.areaname=datlf.areaname and atl.Year=datlf.Year and atl.Month=datlf.Month and atl.Day=datlf.Day "+
        "and rc.Id=atl.ResolutionCodeId and mc.id=atl.mapcodeid and atc.id=atl.AreaTypeCodeId and atl.DateTime=datlf.DateTime "+
        "order by datlf.DateTime";
		try {
					return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						AVFRecordForSpecificDay dataLoad = new AVFRecordForSpecificDay();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDay(rs.getInt(7));
						dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(8));
						dataLoad.setActualTotalLoadValue(rs.getDouble(9));
						return dataLoad;

					});
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<AVFRecordForSpecificMonth> fetchActualvsForecastForSpecificMonth(String areaName, String resolution, YearMonth yearMonth) throws DataAccessException {

        Integer year = yearMonth.getYear();
        Integer month = yearMonth.getMonthValue();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month,
        };

        //TODO: Insert a valid SQL query
        String sqlQuery = "select datlf.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, datlf.year, datlf.month, "+
        "datlf.day, sum(datlf.TotalLoadValue), sum(atl.TotalLoadValue) "+
        "from dayaheadtotalloadforecast as datlf, actualtotalload as atl, resolutioncode as rc, areatypecode as atc, mapcode as mc "+
        "where datlf.areaname=? and rc.resolutioncodetext=? and datlf.Year=? and datlf.Month=? "+
        "and rc.Id=datlf.ResolutionCodeId and mc.id=datlf.mapcodeid and atc.id=datlf.AreaTypeCodeId and "+
        "atl.areaname=datlf.areaname and atl.Year=datlf.Year and atl.Month=datlf.Month "+
        "and rc.Id=atl.ResolutionCodeId and mc.id=atl.mapcodeid and atc.id=atl.AreaTypeCodeId and atl.DateTime=datlf.DateTime "+
        "group by datlf.day, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext order by datlf.day";
		try {
					return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						AVFRecordForSpecificMonth dataLoad = new AVFRecordForSpecificMonth();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDay(rs.getInt(7));
						dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(8));
						dataLoad.setActualTotalLoadValue(rs.getDouble(9));
						return dataLoad;

					});
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<AVFRecordForSpecificYear> fetchActualvsForecastForSpecificYear(String areaName, String resolution, Year year) throws DataAccessException {

        Integer yearParam = year.getValue();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                yearParam,

        };

        //TODO: Insert a valid SQL query
        String sqlQuery = "select datlf.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, datlf.year, datlf.month, "+
        "sum(datlf.TotalLoadValue), sum(atl.TotalLoadValue) "+
        "from dayaheadtotalloadforecast as datlf, actualtotalload as atl, resolutioncode as rc, areatypecode as atc, mapcode as mc "+
        "where datlf.areaname=? and rc.resolutioncodetext=? and datlf.Year=? "+
        "and rc.Id=datlf.ResolutionCodeId and mc.id=datlf.mapcodeid and atc.id=datlf.AreaTypeCodeId and "+
        "atl.areaname=datlf.areaname and atl.Year=datlf.Year "+
        "and rc.Id=atl.ResolutionCodeId and mc.id=atl.mapcodeid and atc.id=atl.AreaTypeCodeId and atl.DateTime=datlf.DateTime "+
        "group by datlf.month, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext order by datlf.month";
		try {
					return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						AVFRecordForSpecificYear dataLoad = new AVFRecordForSpecificYear();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(7));
						dataLoad.setActualTotalLoadValue(rs.getDouble(8));
						return dataLoad;

					});
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public User Login(String userName, String password) throws DataAccessException {
        
        Object[] sqlParams = new Object[] {
            userName
        };

        //TODO: Insert a valid SQL query
        String sqlQuery = "select username, email, password, quotas, admin from users where username = ?";
        
        try {
                    return jdbcTemplate.queryForObject(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                        User dataLoad = new User();
                        dataLoad.setUserName(rs.getString(1)); //get the string located at the 1st column of the result set
                        dataLoad.setEmail(rs.getString(2)); //get the int located at the 2nd column of the result set
                        dataLoad.setPassword(rs.getString(3));
                        dataLoad.setRequestsPerDayQuota(rs.getInt(4));
                        dataLoad.setAdmin(rs.getInt(5));
                        if(password.equals(dataLoad.getPassword())) return dataLoad;
                        else return null;
                    });
        }
        
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }

    }

}