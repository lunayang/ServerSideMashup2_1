<?xml version="1.0" encoding="UTF-8"?>




<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" indent="yes"
                omit-xml-declaration="yes"
                doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
                doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"/> 

    <xsl:template match="/">
        

                

                <ul>
                    <xsl:apply-templates select="rss/channel/item">

                    </xsl:apply-templates>

                </ul>
                </xsl:template>

    <xsl:template match="node()">

        <li>
            <xsl:element name="a">
                <xsl:attribute name="href">
                    <xsl:value-of select="link" />
         
                </xsl:attribute>
                <xsl:value-of select="title" />
            </xsl:element>
        </li>

    </xsl:template>

</xsl:stylesheet>   	
