Xatkit Giphy Platform
=====

[![License Badge](https://img.shields.io/badge/license-EPL%202.0-brightgreen.svg)](https://opensource.org/licenses/EPL-2.0)
[![Wiki Badge](https://img.shields.io/badge/doc-wiki-blue)](https://github.com/xatkit-bot-platform/xatkit/wiki/Xatkit-Giphy-Platform)

Integrate GIFs from [Giphy](https://giphy.com/) in your execution model.


## Providers

The Giphy platform does not define any provider.

## Actions

| Action | Parameters                                                   | Return                         | Return Type | Description                                                 |
| ------ | ------------------------------------------------------------ | ------------------------------ | ----------- | ----------------------------------------------------------- |
| GetGif | - `search`(**String**): the search query used to retrieve GIFs (can be a single word or a sentence) | The `url` of the retrieved GIF | String      | Returns the first GIF matching the provided `search` query. |

## Options

The Giphy platform supports the following configuration options

| Key                  | Values | Description                                                  | Constraint    |
| -------------------- | ------ | ------------------------------------------------------------ | ------------- |
| `xatkit.giphy.token` | String | The [developer](https://developers.giphy.com/) token to use to query the Giphy API | **Mandatory** |

