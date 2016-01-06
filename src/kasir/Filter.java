/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kasir;

import java.sql.*;
import sak.KasirException;
/**
 *
 * @author kedra
 */
public interface Filter<T> {
    public String asWhereClause();
    public String asWhereClauseExact();
}