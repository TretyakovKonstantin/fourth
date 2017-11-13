package io.coldicyice.aqualung.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "Record", eager = true)
@ViewScoped
public class Record implements Serializable
{
    private Point point;
    private Boolean result;
    private Area area;

    public Record()
    {
        this.point = new Point();
        this.area = new Area();
    }

    public Double getRadius()
    {
        return getArea().getRadius();
    }

    public void setRadius(Double radius)
    {
        this.getArea().setRadius(radius);
    }

    public Point getPoint()
    {
        return point;
    }

    public void setPoint(Point point)
    {
        this.point = point;
    }

    public Area getArea()
    {
        return area;
    }

    public void setArea(Area area)
    {
        this.area = area;
    }

    public void process()
    {
        this.result = area.contains(point);
    }

    public Boolean getResult()
    {
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (point != null ? !point.equals(record.point) : record.point != null) {
            return false;
        }
        if (result != null ? !result.equals(record.result) : record.result != null) {
            return false;
        }
        return area != null ? area.equals(record.area) : record.area == null;
    }

    @Override
    public int hashCode()
    {
        int result1 = point != null ? point.hashCode() : 0;
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (area != null ? area.hashCode() : 0);
        return result1;
    }
}
