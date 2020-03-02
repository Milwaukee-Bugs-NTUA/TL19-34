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
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

public class DataAccess {

    private static Status outOfQuotasStatus = new Status(402, "Out of Quotas");

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

    public void resetDatabase() throws DataAccessException {
        try {
            int rows1 = jdbcTemplate.update("delete from users where admin=0");
            int rows2 = jdbcTemplate.update("delete from actualtotalload");
            int rows3 = jdbcTemplate.update("delete from aggregatedgenerationpertype");
            int rows4 = jdbcTemplate.update("delete from dayaheadtotalloadforecast");
            System.out.println("Deleted "+rows1+" rows from Users table!");
            System.out.println("Deleted "+rows2+" rows from ActualTotalLoad table!");
            System.out.println("Deleted "+rows3+" rows from AggregatedGenerationPerType table!");
            System.out.println("Deleted "+rows4+" rows from DayAheadTotalLoadForecast table!");
        } 
        catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<ATLRecordForSpecificDay> fetchActualDataLoadForSpecificDate(String areaName, String resolution, LocalDate date, String userName) throws DataAccessException {

        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();
        User u;
        List<ATLRecordForSpecificDay> records;

        Object [] sqlParamsForUser = new Object [] {userName};

        String sqlQueryForUser = "select username, 'none', 'none', quotas - usedquotas, admin from users where username = ?";

        try {
                u = jdbcTemplate.queryForObject(sqlQueryForUser, sqlParamsForUser, (ResultSet rs, int rowNum) -> {
                User dataLoad = new User();
                dataLoad.setUserName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setEmail(rs.getString(2)); //get the int located at the 2nd column of the result set
                dataLoad.setPassword(rs.getString(3));
                dataLoad.setRequestsPerDayQuota(rs.getInt(4));
                dataLoad.setAdmin(rs.getInt(5));
                if (dataLoad.getRequestsPerDayQuota()<=0) throw new ResourceException(outOfQuotasStatus, "Out of Quotas!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }
        
        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month,
                day
        };

        //TODO: Insert a valid SQL query
        String sqlQuery = "select atl.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, atl.year, atl.month, atl.day, atl.datetime, atl.TotalLoadValue, atl.updatetime "+
                          "from actualtotalload as atl, resolutioncode as rc, areatypecode as atc, mapcode as mc " +
                          "where atl.areaname=? and rc.resolutioncodetext=? and atl.Year=? and atl.Month=? and atl.Day=? " +
                          "and rc.Id=atl.ResolutionCodeId and mc.id=atl.mapcodeid and atc.id=atl.AreaTypeCodeId order by atl.datetime";
		try {
					records = jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						ATLRecordForSpecificDay dataLoad = new ATLRecordForSpecificDay();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
                        dataLoad.setDay(rs.getInt(7));
                        dataLoad.setDateTimeUTC(rs.getTimestamp(8));
                        dataLoad.setActualTotalLoadValue(rs.getDouble(9));
                        dataLoad.setUpdateTimeUTC(rs.getTimestamp(10));
						return dataLoad;

					});
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }

        if(u.getAdmin()==0){
            Object [] sqlParamsForQuotas = new Object [] {userName};

            String sqlQueryForQuotas = "update users set usedquotas = usedquotas + 1 where username = ?";

            try {
                int rows1 = jdbcTemplate.update(sqlQueryForQuotas, sqlParamsForQuotas);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        return records;
    }

    public List<DATLFRecordForSpecificDay> fetchDayAheadTotalLoadForecastForSpecificDate(String areaName, String resolution, LocalDate date, String userName) throws DataAccessException {

        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();
        User u;
        List<DATLFRecordForSpecificDay> records;

        Object [] sqlParamsForUser = new Object [] {userName};

        String sqlQueryForUser = "select username, 'none', 'none', quotas - usedquotas, admin from users where username = ?";

        try {
                u = jdbcTemplate.queryForObject(sqlQueryForUser, sqlParamsForUser, (ResultSet rs, int rowNum) -> {
                User dataLoad = new User();
                dataLoad.setUserName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setEmail(rs.getString(2)); //get the int located at the 2nd column of the result set
                dataLoad.setPassword(rs.getString(3));
                dataLoad.setRequestsPerDayQuota(rs.getInt(4));
                dataLoad.setAdmin(rs.getInt(5));
                if (dataLoad.getRequestsPerDayQuota()<=0) throw new ResourceException(outOfQuotasStatus, "Out of Quotas!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }


        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month,
                day
        };

        //TODO: Insert a valid SQL query
        String sqlQuery = "select datlf.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, datlf.year, datlf.month, datlf.day, datlf.datetime, datlf.TotalLoadValue, datlf.updatetime "+
                          "from dayaheadtotalloadforecast as datlf, resolutioncode as rc, areatypecode as atc, mapcode as mc " +
                          "where datlf.areaname=? and rc.resolutioncodetext=? and datlf.Year=? and datlf.Month=? and datlf.Day=? " +
                          "and rc.Id=datlf.ResolutionCodeId and mc.id=datlf.mapcodeid and atc.id=datlf.AreaTypeCodeId";
		try {
					records = jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						DATLFRecordForSpecificDay dataLoad = new DATLFRecordForSpecificDay();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
                        dataLoad.setDay(rs.getInt(7));
                        dataLoad.setDateTimeUTC(rs.getTimestamp(8));
                        dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(9));
                        dataLoad.setUpdateTimeUTC(rs.getTimestamp(10));
						return dataLoad;

					});
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
        
        if(u.getAdmin()==0){
            Object [] sqlParamsForQuotas = new Object [] {userName};

            String sqlQueryForQuotas = "update users set usedquotas = usedquotas + 1 where username = ?";

            try {
                int rows1 = jdbcTemplate.update(sqlQueryForQuotas, sqlParamsForQuotas);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        return records;
    }

    public List<DATLFRecordForSpecificMonth> fetchDayAheadTotalLoadForecastForSpecificMonth(String areaName, String resolution, YearMonth yearMonth, String userName) throws DataAccessException {

        Integer year = yearMonth.getYear();
        Integer month = yearMonth.getMonthValue();
        User u;
        List<DATLFRecordForSpecificMonth> records;

        Object [] sqlParamsForUser = new Object [] {userName};

        String sqlQueryForUser = "select username, 'none', 'none', quotas - usedquotas, admin from users where username = ?";

        try {
                u = jdbcTemplate.queryForObject(sqlQueryForUser, sqlParamsForUser, (ResultSet rs, int rowNum) -> {
                User dataLoad = new User();
                dataLoad.setUserName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setEmail(rs.getString(2)); //get the int located at the 2nd column of the result set
                dataLoad.setPassword(rs.getString(3));
                dataLoad.setRequestsPerDayQuota(rs.getInt(4));
                dataLoad.setAdmin(rs.getInt(5));
                if (dataLoad.getRequestsPerDayQuota()<=0) throw new ResourceException(outOfQuotasStatus, "Out of Quotas!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }


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
                        records = jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						DATLFRecordForSpecificMonth dataLoad = new DATLFRecordForSpecificMonth();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDay(rs.getInt(7));
                        dataLoad.setDayAheadTotalLoadForecastByDayValue(rs.getDouble(8));
						return dataLoad;
					});

        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }

        if(u.getAdmin()==0){
            Object [] sqlParamsForQuotas = new Object [] {userName};

            String sqlQueryForQuotas = "update users set usedquotas = usedquotas + 1 where username = ?";

            try {
                int rows1 = jdbcTemplate.update(sqlQueryForQuotas, sqlParamsForQuotas);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        return records;
    }
    public List<DATLFRecordForSpecificYear> fetchDayAheadTotalLoadForecastForSpecificYear(String areaName, String resolution, Year year, String userName) throws DataAccessException {

        Integer yearInt = year.getValue();

        User u;
        List<DATLFRecordForSpecificYear> records;

        Object [] sqlParamsForUser = new Object [] {userName};

        String sqlQueryForUser = "select username, 'none', 'none', quotas - usedquotas, admin from users where username = ?";

        try {
                u = jdbcTemplate.queryForObject(sqlQueryForUser, sqlParamsForUser, (ResultSet rs, int rowNum) -> {
                User dataLoad = new User();
                dataLoad.setUserName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setEmail(rs.getString(2)); //get the int located at the 2nd column of the result set
                dataLoad.setPassword(rs.getString(3));
                dataLoad.setRequestsPerDayQuota(rs.getInt(4));
                dataLoad.setAdmin(rs.getInt(5));
                if (dataLoad.getRequestsPerDayQuota()<=0) throw new ResourceException(outOfQuotasStatus, "Out of Quotas!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }


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
					records = jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						DATLFRecordForSpecificYear dataLoad = new DATLFRecordForSpecificYear();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDayAheadTotalLoadForecastByMonthValue(rs.getDouble(7));
						return dataLoad;
					});

        }        
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
        
        if(u.getAdmin()==0){
            Object [] sqlParamsForQuotas = new Object [] {userName};

            String sqlQueryForQuotas = "update users set usedquotas = usedquotas + 1 where username = ?";

            try {
                int rows1 = jdbcTemplate.update(sqlQueryForQuotas, sqlParamsForQuotas);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        return records;
    }
    public List<AGPTRecordForSpecificDay> fetchAggregatedGenerationPerTypeForSpecificDate(String areaName, String resolution, String productionType, LocalDate date, String userName) throws DataAccessException {

        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();
        User u;
        List<AGPTRecordForSpecificDay> records;

        Object [] sqlParamsForUser = new Object [] {userName};

        String sqlQueryForUser = "select username, 'none', 'none', quotas - usedquotas, admin from users where username = ?";

        try {
                u = jdbcTemplate.queryForObject(sqlQueryForUser, sqlParamsForUser, (ResultSet rs, int rowNum) -> {
                User dataLoad = new User();
                dataLoad.setUserName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setEmail(rs.getString(2)); //get the int located at the 2nd column of the result set
                dataLoad.setPassword(rs.getString(3));
                dataLoad.setRequestsPerDayQuota(rs.getInt(4));
                dataLoad.setAdmin(rs.getInt(5));
                if (dataLoad.getRequestsPerDayQuota()<=0) throw new ResourceException(outOfQuotasStatus, "Out of Quotas!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }


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
                       "agpt.day, pt.productiontypetext, agpt.datetime, agpt.actualgenerationoutput, agpt.updatetime "+
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
                       "agpt.day, pt.productiontypetext, agpt.datetime, agpt.actualgenerationoutput, agpt.updatetime "+
                       "from aggregatedgenerationpertype as agpt, resolutioncode as rc, areatypecode as atc, mapcode as mc, productiontype as pt " +
                       "where agpt.areaname=? and rc.resolutioncodetext=? and agpt.Year=? and agpt.Month=? and agpt.Day=? " +
                       "and rc.Id=agpt.ResolutionCodeId and mc.id=agpt.mapcodeid and atc.id=agpt.AreaTypeCodeId and pt.id=agpt.productiontypeid";
        } 
		try {
					records = jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						AGPTRecordForSpecificDay dataLoad = new AGPTRecordForSpecificDay();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
                        dataLoad.setDay(rs.getInt(7));
                        dataLoad.setProductionType(rs.getString(8));
                        dataLoad.setDateTimeUTC(rs.getTimestamp(9));
                        dataLoad.setActualGenerationOutputValue(rs.getDouble(10));
                        dataLoad.setUpdateTimeUTC(rs.getTimestamp(11));
						return dataLoad;

					});
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
        
        if(u.getAdmin()==0){
            Object [] sqlParamsForQuotas = new Object [] {userName};

            String sqlQueryForQuotas = "update users set usedquotas = usedquotas + 1 where username = ?";

            try {
                int rows1 = jdbcTemplate.update(sqlQueryForQuotas, sqlParamsForQuotas);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        return records;
    }

    public List<AGPTRecordForSpecificMonth> fetchAggregatedGenerationPerTypeForSpecificMonth(String areaName, String resolution, String productionType, YearMonth yearMonth, String userName) throws DataAccessException {

        Integer year = yearMonth.getYear();
        Integer month = yearMonth.getMonthValue();
        User u;
        List<AGPTRecordForSpecificMonth> records;

        Object [] sqlParamsForUser = new Object [] {userName};

        String sqlQueryForUser = "select username, 'none', 'none', quotas - usedquotas, admin from users where username = ?";

        try {
                u = jdbcTemplate.queryForObject(sqlQueryForUser, sqlParamsForUser, (ResultSet rs, int rowNum) -> {
                User dataLoad = new User();
                dataLoad.setUserName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setEmail(rs.getString(2)); //get the int located at the 2nd column of the result set
                dataLoad.setPassword(rs.getString(3));
                dataLoad.setRequestsPerDayQuota(rs.getInt(4));
                dataLoad.setAdmin(rs.getInt(5));
                if (dataLoad.getRequestsPerDayQuota()<=0) throw new ResourceException(outOfQuotasStatus, "Out of Quotas!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }


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
                        records = jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						AGPTRecordForSpecificMonth dataLoad = new AGPTRecordForSpecificMonth();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDay(rs.getInt(7));
                        dataLoad.setProductionType(rs.getString(8));
                        dataLoad.setActualGenerationOutputByDayValue(rs.getDouble(9));
                        return dataLoad;
					});

        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
        
        if(u.getAdmin()==0){
            Object [] sqlParamsForQuotas = new Object [] {userName};

            String sqlQueryForQuotas = "update users set usedquotas = usedquotas + 1 where username = ?";

            try {
                int rows1 = jdbcTemplate.update(sqlQueryForQuotas, sqlParamsForQuotas);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        return records;
    }

    public List<AGPTRecordForSpecificYear> fetchAggregatedGenerationPerTypeForSpecificYear(String areaName, String resolution, String productionType, Year year, String userName) throws DataAccessException {

        Integer yearInt = year.getValue();
        User u;
        List<AGPTRecordForSpecificYear> records;

        Object [] sqlParamsForUser = new Object [] {userName};

        String sqlQueryForUser = "select username, 'none', 'none', quotas - usedquotas, admin from users where username = ?";

        try {
                u = jdbcTemplate.queryForObject(sqlQueryForUser, sqlParamsForUser, (ResultSet rs, int rowNum) -> {
                User dataLoad = new User();
                dataLoad.setUserName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setEmail(rs.getString(2)); //get the int located at the 2nd column of the result set
                dataLoad.setPassword(rs.getString(3));
                dataLoad.setRequestsPerDayQuota(rs.getInt(4));
                dataLoad.setAdmin(rs.getInt(5));
                if (dataLoad.getRequestsPerDayQuota()<=0) throw new ResourceException(outOfQuotasStatus, "Out of Quotas!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }


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
					records = jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						AGPTRecordForSpecificYear dataLoad = new AGPTRecordForSpecificYear();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setMonth(rs.getInt(6));
                        dataLoad.setProductionType(rs.getString(7));
                        dataLoad.setActualGenerationOutputByMonthValue(rs.getDouble(8));
						return dataLoad;
					});

        }        
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
        
        if(u.getAdmin()==0){
            Object [] sqlParamsForQuotas = new Object [] {userName};

            String sqlQueryForQuotas = "update users set usedquotas = usedquotas + 1 where username = ?";

            try {
                int rows1 = jdbcTemplate.update(sqlQueryForQuotas, sqlParamsForQuotas);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        return records;
    }

    public List<ATLRecordForSpecificMonth> fetchActualDataLoadForSpecificMonth(String areaName, String resolution, YearMonth yearMonth, String userName) throws DataAccessException {
            
        Integer year = yearMonth.getYear();
        Integer month = yearMonth.getMonthValue();
        User u;
        List<ATLRecordForSpecificMonth> records;

        Object [] sqlParamsForUser = new Object [] {userName};

        String sqlQueryForUser = "select username, 'none', 'none', quotas - usedquotas, admin from users where username = ?";

        try {
                u = jdbcTemplate.queryForObject(sqlQueryForUser, sqlParamsForUser, (ResultSet rs, int rowNum) -> {
                User dataLoad = new User();
                dataLoad.setUserName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setEmail(rs.getString(2)); //get the int located at the 2nd column of the result set
                dataLoad.setPassword(rs.getString(3));
                dataLoad.setRequestsPerDayQuota(rs.getInt(4));
                dataLoad.setAdmin(rs.getInt(5));
                if (dataLoad.getRequestsPerDayQuota()<=0) throw new ResourceException(outOfQuotasStatus, "Out of Quotas!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }

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
            records = jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
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

        if(u.getAdmin()==0){
            Object [] sqlParamsForQuotas = new Object [] {userName};

            String sqlQueryForQuotas = "update users set usedquotas = usedquotas + 1 where username = ?";

            try {
                int rows1 = jdbcTemplate.update(sqlQueryForQuotas, sqlParamsForQuotas);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        return records;
    }

    public List<ATLRecordForSpecificYear> fetchActualDataLoadForSpecificYear(String areaName, String resolution, Year year, String userName) throws DataAccessException {
        Integer year1 = year.getValue();
        User u;
        List<ATLRecordForSpecificYear> records;

        Object [] sqlParamsForUser = new Object [] {userName};

        String sqlQueryForUser = "select username, 'none', 'none', quotas - usedquotas, admin from users where username = ?";

        try {
                u = jdbcTemplate.queryForObject(sqlQueryForUser, sqlParamsForUser, (ResultSet rs, int rowNum) -> {
                User dataLoad = new User();
                dataLoad.setUserName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setEmail(rs.getString(2)); //get the int located at the 2nd column of the result set
                dataLoad.setPassword(rs.getString(3));
                dataLoad.setRequestsPerDayQuota(rs.getInt(4));
                dataLoad.setAdmin(rs.getInt(5));
                if (dataLoad.getRequestsPerDayQuota()<=0) throw new ResourceException(outOfQuotasStatus, "Out of Quotas!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }

        
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
            records = jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
            ATLRecordForSpecificYear dataLoad = new ATLRecordForSpecificYear();
            dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
            dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
            dataLoad.setMapCode(rs.getString(3));
            dataLoad.setResolutionCode(rs.getString(4));
            dataLoad.setYear(rs.getInt(5));
            dataLoad.setMonth(rs.getInt(6));
            //dataLoad.setDay(rs.getInt(7));
            dataLoad.setActualTotalLoadByMonthValue(rs.getDouble(7));
            return dataLoad;

            });
        }
        catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
        }

        if(u.getAdmin()==0){
            Object [] sqlParamsForQuotas = new Object [] {userName};

            String sqlQueryForQuotas = "update users set usedquotas = usedquotas + 1 where username = ?";

            try {
                int rows1 = jdbcTemplate.update(sqlQueryForQuotas, sqlParamsForQuotas);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        return records;
    }

    public List<AVFRecordForSpecificDay> fetchActualvsForecastForSpecificDate(String areaName, String resolution, LocalDate date, String userName) throws DataAccessException {

        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();
        User u;
        List<AVFRecordForSpecificDay> records;

        Object [] sqlParamsForUser = new Object [] {userName};

        String sqlQueryForUser = "select username, 'none', 'none', quotas - usedquotas, admin from users where username = ?";

        try {
                u = jdbcTemplate.queryForObject(sqlQueryForUser, sqlParamsForUser, (ResultSet rs, int rowNum) -> {
                User dataLoad = new User();
                dataLoad.setUserName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setEmail(rs.getString(2)); //get the int located at the 2nd column of the result set
                dataLoad.setPassword(rs.getString(3));
                dataLoad.setRequestsPerDayQuota(rs.getInt(4));
                dataLoad.setAdmin(rs.getInt(5));
                if (dataLoad.getRequestsPerDayQuota()<=0) throw new ResourceException(outOfQuotasStatus, "Out of Quotas!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }


        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month,
                day
        };

        //TODO: Insert a valid SQL query
        String sqlQuery = "select datlf.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, datlf.year, datlf.month, "+
        "datlf.day, datlf.DateTime, datlf.TotalLoadValue, atl.TotalLoadValue, datlf.UpdateTime "+
        "from dayaheadtotalloadforecast as datlf, actualtotalload as atl, resolutioncode as rc, areatypecode as atc, mapcode as mc "+
        "where datlf.areaname=? and rc.resolutioncodetext=? and datlf.Year=? and datlf.Month=? and datlf.Day=? "+
        "and rc.Id=datlf.ResolutionCodeId and mc.id=datlf.mapcodeid and atc.id=datlf.AreaTypeCodeId and "+
        "atl.areaname=datlf.areaname and atl.Year=datlf.Year and atl.Month=datlf.Month and atl.Day=datlf.Day "+
        "and rc.Id=atl.ResolutionCodeId and mc.id=atl.mapcodeid and atc.id=atl.AreaTypeCodeId and atl.DateTime=datlf.DateTime "+
        "order by datlf.DateTime";
		try {
					records = jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						AVFRecordForSpecificDay dataLoad = new AVFRecordForSpecificDay();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
                        dataLoad.setDay(rs.getInt(7));
                        dataLoad.setDateTimeUTC(rs.getTimestamp(8));
						dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(9));
                        dataLoad.setActualTotalLoadValue(rs.getDouble(10));
                        dataLoad.setUpdateTimeUTC(rs.getTimestamp(11));
						return dataLoad;

					});
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
        if(u.getAdmin()==0){
            Object [] sqlParamsForQuotas = new Object [] {userName};

            String sqlQueryForQuotas = "update users set usedquotas = usedquotas + 1 where username = ?";

            try {
                int rows1 = jdbcTemplate.update(sqlQueryForQuotas, sqlParamsForQuotas);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        return records;
    }

    public List<AVFRecordForSpecificMonth> fetchActualvsForecastForSpecificMonth(String areaName, String resolution, YearMonth yearMonth, String userName) throws DataAccessException {

        Integer year = yearMonth.getYear();
        Integer month = yearMonth.getMonthValue();
        User u;
        List<AVFRecordForSpecificMonth> records;

        Object [] sqlParamsForUser = new Object [] {userName};

        String sqlQueryForUser = "select username, 'none', 'none', quotas - usedquotas, admin from users where username = ?";

        try {
                u = jdbcTemplate.queryForObject(sqlQueryForUser, sqlParamsForUser, (ResultSet rs, int rowNum) -> {
                User dataLoad = new User();
                dataLoad.setUserName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setEmail(rs.getString(2)); //get the int located at the 2nd column of the result set
                dataLoad.setPassword(rs.getString(3));
                dataLoad.setRequestsPerDayQuota(rs.getInt(4));
                dataLoad.setAdmin(rs.getInt(5));
                if (dataLoad.getRequestsPerDayQuota()<=0) throw new ResourceException(outOfQuotasStatus, "Out of Quotas!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }


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
					records = jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						AVFRecordForSpecificMonth dataLoad = new AVFRecordForSpecificMonth();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDay(rs.getInt(7));
						dataLoad.setDayAheadTotalLoadForecastByDayValue(rs.getDouble(8));
						dataLoad.setActualTotalLoadByDayValue(rs.getDouble(9));
						return dataLoad;

					});
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
        if(u.getAdmin()==0){
            Object [] sqlParamsForQuotas = new Object [] {userName};

            String sqlQueryForQuotas = "update users set usedquotas = usedquotas + 1 where username = ?";

            try {
                int rows1 = jdbcTemplate.update(sqlQueryForQuotas, sqlParamsForQuotas);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        return records;
    }

    public List<AVFRecordForSpecificYear> fetchActualvsForecastForSpecificYear(String areaName, String resolution, Year year, String userName) throws DataAccessException {

        Integer yearParam = year.getValue();
        User u;
        List<AVFRecordForSpecificYear> records;

        Object [] sqlParamsForUser = new Object [] {userName};

        String sqlQueryForUser = "select username, 'none', 'none', quotas - usedquotas, admin from users where username = ?";

        try {
                u = jdbcTemplate.queryForObject(sqlQueryForUser, sqlParamsForUser, (ResultSet rs, int rowNum) -> {
                User dataLoad = new User();
                dataLoad.setUserName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setEmail(rs.getString(2)); //get the int located at the 2nd column of the result set
                dataLoad.setPassword(rs.getString(3));
                dataLoad.setRequestsPerDayQuota(rs.getInt(4));
                dataLoad.setAdmin(rs.getInt(5));
                if (dataLoad.getRequestsPerDayQuota()<=0) throw new ResourceException(outOfQuotasStatus, "Out of Quotas!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }


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
					records = jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						AVFRecordForSpecificYear dataLoad = new AVFRecordForSpecificYear();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDayAheadTotalLoadForecastByMonthValue(rs.getDouble(7));
						dataLoad.setActualTotalLoadByMonthValue(rs.getDouble(8));
						return dataLoad;

					});
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
        
        if(u.getAdmin()==0){
            Object [] sqlParamsForQuotas = new Object [] {userName};

            String sqlQueryForQuotas = "update users set usedquotas = usedquotas + 1 where username = ?";

            try {
                int rows1 = jdbcTemplate.update(sqlQueryForQuotas, sqlParamsForQuotas);
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        return records;
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

    public User addUser(String adminUserName, String userName, String password, String email, int requestsPerDayQuota) throws DataAccessException {
        int admin;

        Object [] sqlParamsForAdmin = new Object [] {adminUserName};

        String sqlQueryForAdmin = "select admin from users where username = ?";

        try {
                admin = jdbcTemplate.queryForObject(sqlQueryForAdmin, sqlParamsForAdmin, (ResultSet rs, int rowNum) -> {
                int dataLoad = rs.getInt(1);
                if (dataLoad==0) throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED, "User has no Admin Priveleges!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }

        Object [] sqlParams = new Object [] {userName, email, password, requestsPerDayQuota};

        String sqlQuery = "insert into users (username, email, password, quotas, admin, usedquotas) values "+
                                   "(?, ?, ?, ?, 0, 0)";

        try {
            int rows1 = jdbcTemplate.update(sqlQuery, sqlParams);
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
        return new User(userName, email, 0, requestsPerDayQuota); 
    }
    public User updateUser(String adminUserName, String userName, String password, String email, int requestsPerDayQuota) throws DataAccessException {
        int admin;

        Object [] sqlParamsForAdmin = new Object [] {adminUserName};

        String sqlQueryForAdmin = "select admin from users where username = ?";

        try {
                admin = jdbcTemplate.queryForObject(sqlQueryForAdmin, sqlParamsForAdmin, (ResultSet rs, int rowNum) -> {
                int dataLoad = rs.getInt(1);
                if (dataLoad==0) throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED, "User has no Admin Priveleges!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }

        Object [] sqlParamsForUpdate = new Object [] {email, password, requestsPerDayQuota, userName};
        String sqlQueryForUpdate = "update users set email = ?, password = ?, quotas = ? where username = ?";

        try {
            int rows1 = jdbcTemplate.update(sqlQueryForUpdate, sqlParamsForUpdate);
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
        
        Object [] sqlParamsForUser = new Object [] {userName};

        String sqlQueryForUser = "select username, email, quotas, admin, usedquotas from users where username = ?";

        try {
            return jdbcTemplate.queryForObject(sqlQueryForUser, sqlParamsForUser, (ResultSet rs, int rowNum) -> {
                User dataLoad = new User();
                dataLoad.setUserName(rs.getString(1)); 
                dataLoad.setEmail(rs.getString(2));
                dataLoad.setRequestsPerDayQuota(rs.getInt(3));
                dataLoad.setAdmin(rs.getInt(4));
                dataLoad.setUsedPerDayQuota(rs.getInt(5));
                return dataLoad;
            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public User getUser(String adminUserName, String userName) throws DataAccessException {
    
        int admin;

        Object [] sqlParamsForAdmin = new Object [] {adminUserName};

        String sqlQueryForAdmin = "select admin from users where username = ?";

        try {
                admin = jdbcTemplate.queryForObject(sqlQueryForAdmin, sqlParamsForAdmin, (ResultSet rs, int rowNum) -> {
                int dataLoad = rs.getInt(1);
                if (dataLoad==0) throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED, "User has no Admin Priveleges!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }        
        
        Object [] sqlParams = new Object [] {userName};

        String sqlQuery = "select username, email, quotas, admin, usedquotas from users where username = ?";

        try {
            return jdbcTemplate.queryForObject(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                User dataLoad = new User();
                dataLoad.setUserName(rs.getString(1)); 
                dataLoad.setEmail(rs.getString(2));
                dataLoad.setRequestsPerDayQuota(rs.getInt(3));
                dataLoad.setAdmin(rs.getInt(4));
                dataLoad.setUsedPerDayQuota(rs.getInt(5));
                return dataLoad;
            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public int[] importActualTotalLoadDataSet(String adminUserName, String fileName) throws DataAccessException {
        int admin;

        Object [] sqlParamsForAdmin = new Object [] {adminUserName};

        String sqlQueryForAdmin = "select admin from users where username = ?";

        try {
                admin = jdbcTemplate.queryForObject(sqlQueryForAdmin, sqlParamsForAdmin, (ResultSet rs, int rowNum) -> {
                int dataLoad = rs.getInt(1);
                if (dataLoad==0) throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED, "User has no Admin Priveleges!");
                return dataLoad;
            });
        }

        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }

        // SQL query for db configuration
        String sqlQueryForLocalInfile = "SET GLOBAL local_infile = 1";

        try {
            int rows1 = jdbcTemplate.update(sqlQueryForLocalInfile);
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }

        // SQL query for import data
        String fullPath = System.getProperty("user.home")+ "/"+ fileName;
        Object [] sqlParamsForImport = new Object [] {fullPath};
        int rows2 = 0;
        String sqlQueryForImport = "load data local infile ? into table actualtotalload FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n' IGNORE 1 LINES";
        try {
            rows2 = jdbcTemplate.update(sqlQueryForImport, sqlParamsForImport);
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }

        int rows3 = 0;
        String sqlQueryForCountingRecords = "select count(*) from actualtotalload";
        try {
            rows3 = jdbcTemplate.queryForObject(sqlQueryForCountingRecords, (ResultSet rs, int rowNum) -> {
            int dataLoad = rs.getInt(1);
            return dataLoad;
            });
        }

        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
        

        return new int [] {rows2,rows3};
    }
}