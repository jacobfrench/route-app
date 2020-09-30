#!/usr/bin/python3
import googlemaps
import time


if __name__ == "__main__":
    gmaps = googlemaps.Client(key="AIzaSyChgIS9UKbb-hc-NKEnxLhkzWH4wYVmHXw")

    file = open("raw_address.txt", "r")
    lines = file.readlines()

    out = open('out.txt', 'w')
    for line in lines:
        line = line.replace("\n", "")
        geocode_result = gmaps.geocode(line)
        location = geocode_result[0]["geometry"]['location']
        line_out = str("%s,%s,%s\n" % (line, location['lat'], location['lng']))
        out.write(line_out)
        print(line_out)
        time.sleep(1)
    out.close()
